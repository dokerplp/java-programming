package com.company.run;

import com.company.utility.forData.Fields;
import com.company.commandPattern.CommandsOperator;
import com.company.commandPattern.User;
import com.company.data.Product;
import com.company.utility.forCommands.RequestsQueue;
import com.company.utility.forData.DataBase;
import com.company.utility.forData.DataOperator;
import com.company.utility.forData.DataProcessor;
import com.company.utility.json.JSON_Breaker;

import java.io.IOException;
import java.util.*;

/**
 * Main class
 */
public class Client {

    /**
     * main method
     */
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>(); //коллекция
        RequestsQueue queue = new RequestsQueue(); //очередь из комманд
        Fields fields = new Fields(); //Общие поля класса

        DataOperator data = new DataOperator(queue); //Утилита для получения данных
        DataBase base = new DataBase(products, queue, fields, data); //База данных хранит коллекцию, очередь и общие переменные

        User user = new User(); //Юзер для паттерна
        CommandsOperator operator = new CommandsOperator(user, base); //Ресивер для паттерна

        try{
            new JSON_Breaker(base); //Класс для преобразования json в Обьект
            Collections.sort(products); //Сортировка полученных обьектов через Comparable
        }catch (RuntimeException | IOException e){
            System.out.println("Не удалось загрузить коллекцию из файла");
        } //Загрузка данных из файла json

        while (true){
            queue.setStatus(true); //Очередью управляет человек
            fields.Putin(); //Обнулить Счетчик рекурсий
            String push = null; //
            try{
                push = data.getSTRING(); //Получить новую строку комманды от пользователя
            } catch (NoSuchElementException e){ //Если нажали Control D
                System.out.println("КЧАУ!");
                System.exit(0);
            }
            if (push != null) queue.pushOne(push); //Если строка не null, то добавить ее в очрердь
            while (queue.getElementAmount() != 0) { //Пока в очереди есть комманды, выполняй их
                String line = queue.pollOne(); //Очередная комманда
                if (line != null) { //Если она не null
                    String command = DataProcessor.getCommand(line); //Получить название комманды
                    String argument = DataProcessor.getArgument(line); //Получить аргумент
                    operator.execute(command, argument); //Выполнить комманду
                }
            }
        }
    }
}
