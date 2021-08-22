package com.company.run;

import com.company.commandPattern.CommandsOperator;
import com.company.commandPattern.SaveCommand;
import com.company.commandPattern.User;
import com.company.data.Product;
import com.company.memory.Registers;
import com.company.memory.Vault;
import com.company.utility.Log;
import com.company.utility.json.JSONBreaker;

import java.util.*;

public class Server {

    public static void main(String[] args) {
        Log log = new Log();
        try {

            List<Product> products = new ArrayList<>();
            Registers registers = new Registers();
            Vault vault = new Vault(products, registers);

            SaveCommand save = new SaveCommand(vault);

            Runtime.getRuntime().addShutdownHook(new Thread(save));

            User user = new User();

            CommandsOperator operator = new CommandsOperator(user, vault);

            int PORT = 2113;
            try {
                PORT = Integer.parseInt(args[0]);
            } catch (Exception e) {
                log.exception(e);
            }
            log.turnOn(PORT);

            try {
                new JSONBreaker(vault);
                Collections.sort(vault.getCollection());
                vault.getRegisters().setSavesAllowed(true);
            } catch (Exception e) {
                System.err.println("Не удалось загрузить коллекцию из файла");
                log.exception(e);
                vault.getRegisters().setSavesAllowed(false);
            }

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    try {
                        String line = scanner.nextLine().toLowerCase();
                        if (line.equals("save")) save.execute();
                        else if (line.equals("exit")) {
                            log.turnOff("Command exit");
                            System.exit(0);
                        }
                    } catch (NoSuchElementException e) {
                        log.exception(e);
                        log.turnOff("Closed input stream");
                    }
                }
            }).start();

            new Thread(new IOmodule(PORT, operator)).start();

        } catch (Throwable e) {
            log.fatalTurnOff(e);
            throw e;
        }
    }
}