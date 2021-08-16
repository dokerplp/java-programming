package com.company.run;

import com.company.commandPattern.CommandsOperator;
import com.company.commandPattern.User;
import com.company.memory.Clients;
import com.company.memory.Vault;
import com.company.utility.Exit;
import com.company.utility.Input;
import com.company.utility.Log;
import com.company.utility.database.DataBaseConnection;
import com.company.utility.database.SQL;
import com.company.utility.requests.Worker;

import java.io.IOException;
import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {

    public static void main(String[] args) {
        Log log = new Log();
        AtomicBoolean exit = new AtomicBoolean(false);
        Thread work = null;
        try {
            Vault vault = new Vault();

            DataBaseConnection base = new DataBaseConnection();
            Connection connection = base.makeConnection();

            Clients clients = new Clients();
            SQL sql = new SQL(connection);
            User user = new User(clients);
            CommandsOperator operator = new CommandsOperator(sql, vault);
            operator.setCommands(user);

            int PORT = 2113;
            try {
                PORT = Integer.parseInt(args[0]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored) {
            }
            log.turnOn(PORT);

            vault.addAll(sql.selectAll());

            new Thread(() -> {
                while (true) {
                    String line = Input.SCANNER.nextLine().toLowerCase();
                    if (line.equals("exit")) {
                        log.turnOff("Command exit");
                        exit.set(true);
                        throw new Exit();
                    }
                }
            }).start();

            work = new Thread(new Worker(sql, PORT, clients, user));
            work.setDaemon(true);
            work.start();

            while (true){
                if (exit.get()) throw new Exit();
            }

        } catch (Exit | IOException suicide){
            log.exception(suicide);
            if (work != null) work.interrupt();
            System.out.println("Приложение завершает работу");
        }
    }
}