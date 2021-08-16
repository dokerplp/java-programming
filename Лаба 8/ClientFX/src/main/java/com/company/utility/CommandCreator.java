package com.company.utility;

import com.company.data.io.Mail;
import com.company.exceptions.Exit;
import com.company.javaFX.ModalWindow;
import com.company.javaFX.Language;


public class CommandCreator {

    private final Authorization authorization;

    public CommandCreator(Authorization authorization) {
        this.authorization = authorization;
    }

    public void mailCreate(String command, Language language){
        String LOGIN = authorization.getLOGIN();
        String PASS = authorization.getPASS();
        switch (command) {
            case "add":
            case "add_if_max":
               ModalWindow.showAddWindow(language, authorization.connect(), command, authorization);
            case "average_of_price":
            case "clear":
            case "help":
            case "info":
            case "shuffle":
                ServerHandler.send(new Mail(command, LOGIN, PASS), authorization.connect());
            case "count_by_manufacture_cost":
            case "count_less_than_manufacture_cost":
            case "remove_by_id":
            case "update":
                ModalWindow.showCommandWithArgumentWindow(language, command);
            case "exit":
                throw new Exit();
            case "remove_greater":
                ServerHandler.send(new Mail(command, LOGIN, PASS), authorization.connect());
        }
    }
}
