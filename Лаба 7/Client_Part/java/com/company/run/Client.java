package com.company.run;

import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.exceptions.Exit;
import com.company.memory.Registers;
import com.company.memory.Vault;
import com.company.utility.*;
import com.company.utility.staticUtil.DataOperator;
import com.company.utility.staticUtil.ProductOperator;

import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        try {

//            System.setProperty("https.proxyHost", "myProxy");
//            System.setProperty("https.proxyPort", "80");

            Registers registers = new Registers();
            Vault vault = new Vault();
            DataOperator data = new DataOperator(vault);
            ProductOperator operator = new ProductOperator(registers, data);

            Authentication authentication = new Authentication(args);
            authentication.register(registers);
            CommandCreator creator = new CommandCreator(authentication, vault, registers, operator, data); //Утилита для создания нового письма

            ClientHandler clientHandler = new ClientHandler(creator, vault, registers);

            Input.ScannerHandler SCANNER = Input.SCANNER;

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                Socket con;
                if ((con = authentication.lastConnect()) != null)
                    ServerHandler.send(new Mail("disconnect", authentication.getLOGIN(), authentication.getPASS()), con, registers);
            }));

            while (true) {

                registers.modeTrue();
                registers.clearRecursion();

                String line;
                if ((line = SCANNER.nextLine()) != null) vault.pushOne(line);

                while (vault.getElementAmount() > 0 || !registers.getUpdateTrigger()) {
                    Mail mail;
                    if ((mail = clientHandler.mailCreator()) == null) continue;
                    if (ServerHandler.send(mail, authentication.connect(), registers) == RequestResult.NEED_RECONNECTION)
                        authentication.register(registers);
                }
            }
        } catch (Exit e) {
            System.out.println("Приложение заканчивает работу");
        }
    }

}
