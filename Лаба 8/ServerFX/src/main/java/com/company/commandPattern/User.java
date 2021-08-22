package com.company.commandPattern;

import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;
import com.company.data.io.Verification;
import com.company.memory.Clients;

import java.util.HashMap;

/**
 * Invoker class
 * It knows how how add and execute commands, but dont know list of commands
 */
public class User {

    private static final HashMap<String, Command> commandMap = new HashMap<>();
    private final Clients clients;

    public User(Clients clients) {
        this.clients = clients;
    }

    public static HashMap<String, Command> getCommandMap() {
        return new HashMap<>(commandMap);
    }

    public void addCommand(String Name, Command command) {
        commandMap.put(Name, command);
    }

    public Answer execute(Mail mail, int id) {
        if (clients.contains(mail.getLOGIN(), Verification.hashing(mail.getPASS())) > 0) {
            Command command = commandMap.get(mail.getCommandName());
            if (command == null) {
                if (mail.getCommandName().equals("disconnect")) {
                    clients.disconnect(mail.getLOGIN());
                    clients.remove(mail.getLOGIN(), mail.getPASS());
                    return new Answer("", RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
                } else System.err.println("Client sent wrong command - " + mail.getCommandName());
                return null;
            } else {
                return command.execute(mail, id);
            }
        }
        return null;
    }


}