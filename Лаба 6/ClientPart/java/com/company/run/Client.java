package com.company.run;

import com.company.commands.CommandCreator;
import com.company.commands.ShowCommand;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.exceptions.IncorrectDataException;
import com.company.exceptions.InterruptionFetch;
import com.company.exceptions.NeedArgumentException;
import com.company.exceptions.NotExistingCommandException;
import com.company.memory.Registers;
import com.company.memory.Vault;
import com.company.utility.nonstaticUtil.DataOperator;
import com.company.utility.nonstaticUtil.ProductOperator;
import com.company.utility.staticUtil.Args;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Client {

    public static void main(String[] args) {
        Vault vault = new Vault(); //Хранилеище запросов клиента
        DataOperator data = new DataOperator(vault); //Утилита для работы с данными
        Registers registers = new Registers(); //Регистры состояния приложения
        ProductOperator operator = new ProductOperator(registers, data); //Утилита для создания новых продуктов

        System.out.println("Клиент запущен");

        int updateTrigger = 1;
        Product update = null;

        boolean connection = false;

        int PORT = 2113;
        String ADDR = "127.0.0.1";
        try {
            PORT = args.length > 0 ? Integer.parseInt(args[0]) : 2113;
            ADDR = args.length > 1 ? args[1] : "127.0.0.1";
        } catch (NumberFormatException ignored) {
        }

        Socket socket; //Сокет подключения

        CommandCreator creator = new CommandCreator(operator, registers, data, vault); //Утилита для создания нового письма


        while (true) {
            registers.modeTrue(); //Клиент - человек, а не скрипт
            registers.clearRecursion(); //Новый счетчик рекурсий

            Consumer<String> ClientInput = (line) -> {
                try {
                    if (line != null) vault.pushOne(line);
                } catch (NoSuchElementException e) {
                    System.out.println("Я умер...");
                    System.exit(0);
                }
            }; //Запихни в хранилище новый запрос или крашни приложение при закрытии потока  ввода
            ClientInput.accept(data.getSTRING()); //Запуск лямбды

            while (vault.getElementAmount() > 0 || updateTrigger == -1) {

                //Getting request module

                Mail mail;
                String line;
                String com;
                String arg;
                try {
                    if (updateTrigger == 1) {
                        line = vault.pollOne(); //Взять запрос
                        com = Args.getCommand(line);
                        arg = Args.getArgument(line);
                        if (com == null) continue;
                        else if (com.equals("exit")) System.exit(0);
                        mail = creator.command(com, arg);
                    } else {
                        mail = creator.command(update);
                        updateTrigger *= -1;
                    }
                } catch (NeedArgumentException | InterruptionFetch e) {
                    continue;
                } catch (NotExistingCommandException e) {
                    System.err.println("Введенной комманды не существует, введите help для ознакомления со списком комманд");
                    continue;
                }

                //IO module

                try {
                    socket = new Socket(ADDR, PORT);
                } catch (Exception e) {
                    System.err.println("Ошибка соединения с сервером, ждем подключения!");
                    continue;
                } //Проверь подключение к серверу

                try (
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream out = new ObjectOutputStream(baos);

                        ByteArrayOutputStream os = new ByteArrayOutputStream();
                        DataOutputStream dos = new DataOutputStream(os)
                ) {

                    if (!connection) {
                        mail.setConnect(true);
                        connection = true;
                    } else mail.setConnect(false);


                    ByteArrayOutputStream size = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(size);
                    oos.writeObject(mail);
                    int bytes = size.size();
                    size.close();
                    oos.close();

                    dos.writeInt(bytes);
                    os.writeTo(socket.getOutputStream());
                    dos.close();
                    os.close();

                    out.writeObject(mail);
                    baos.writeTo(socket.getOutputStream());

                    Answer answer = null;
                    try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                        answer = (Answer) ois.readObject();
                    } catch (ClassNotFoundException ignored) {
                    }

                    if (answer == null || answer.getAnswer() == null)
                        System.err.println("Ошибка получения ответа с сервера"); //Ответ null - нас не устраивает


                    else if (answer.getAnswer().equals("show command")) System.out.println(ShowCommand.execute(answer));
                    else if (answer.getAnswer().equals("update")) {
                        updateTrigger *= -1;
                        if (answer.getProducts() != null && !answer.getProducts().isEmpty())
                            update = answer.getProducts().get(0);
                    } else System.out.println(answer.getAnswer());

                } catch (IOException e) {
                    System.err.println("Ошибка отправки запроса на сервер");
                } catch (IncorrectDataException e) {
                    e.outLogs();
                }
            }
        }
    }
}
