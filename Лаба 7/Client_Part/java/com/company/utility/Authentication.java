package com.company.utility;

import com.company.data.io.RequestResult;
import com.company.data.io.Verification;
import com.company.exceptions.Exit;
import com.company.memory.Registers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Authentication {

    private final Input.ScannerHandler SCANNER = Input.SCANNER;

    private int PORT = 2113;
    private String ADDR = "helios.cs.ifmo.ru";

    private String LOGIN;
    private String PASS;

    public Authentication(String... args) {
        try {
            PORT = args.length > 0 ? Integer.parseInt(args[0]) : 2113;
            ADDR = args.length > 1 ? args[1] : "127.0.0.1";
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
        }
    }

    public String getLOGIN() {
        return LOGIN;
    }

    public String getPASS() {
        return PASS;
    }

    public Socket connect() {
        while (true) {
            try {
                return new Socket(InetAddress.getByName(ADDR), PORT);
            } catch (Exception e) {
                System.err.println("Ошибка соединения с сервером, ждем подключения!");
                System.err.println("Введите \"exit\" для выхода, любую другую строку для повторной попытки");
                if (SCANNER.nextLine().equals("exit")) throw new Exit();
            }
        }
    }

    public Socket lastConnect() {
        try {
            return new Socket(ADDR, PORT);
        } catch (IOException e) {
            return null;
        }
    }

    public void register(Registers registers) {
        boolean trigger = false;
        boolean mode;

        do {
            Socket socket = connect();
            System.out.println("1 - Login, 2 - Sign in, 3 - exit");

            label:
            while (true) {
                switch (SCANNER.nextLine()) {
                    case "1":
                        mode = false;
                        break label;
                    case "2":
                        mode = true;
                        break label;
                    case "3":
                        throw new Exit();
                    default:
                        System.err.println("Enter 1, 2 or 3");
                }
            }
            System.out.print("Login: ");
            LOGIN = SCANNER.nextLine();
            System.out.print("Password: ");
            PASS = SCANNER.nextLine();

            if (ServerHandler.send(new Verification(LOGIN, PASS, mode), socket, registers) == RequestResult.REQUEST_COMPLETED_SUCCESSFULLY)
                trigger = true;

        } while (!trigger);
    }
}
