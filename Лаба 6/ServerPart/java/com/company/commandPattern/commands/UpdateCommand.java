package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.Product;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Collection updates fields of element by its id
 */
public class UpdateCommand implements Command {

    private final Vault vault;

    public UpdateCommand(Vault vault) {
        this.vault = vault;
    }

    @Override
    public Answer execute(Mail command) {
        if (command.getCommandName().equals("update_one")) return executeOne(command);
        else if (command.getCommandName().equals("update_two")) return executeTwo(command);
        return null;
    }

    private Answer executeOne(Mail mail) {
        try {
            int id = mail.getIntArgument();

            System.out.println(id); //

            try (
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos)
            ) {
                List<Product> list = vault.getCollection().stream().filter(product -> product.getId() == id).collect(Collectors.toList());
                if (list.isEmpty()) {
                    oos.writeObject(null);
                    return new Answer("Такого элемента не найдено");
                }
                return new Answer("update", list);
            }
        } catch (IOException e) {
            return null;
        }
    }

    private Answer executeTwo(Mail mail) {
        Product product = mail.getProductArgument();
        if (product == null) return null;
        vault.getCollection().removeIf(prod -> product.getId() == prod.getId());
        vault.getCollection().add(product);
        vault.getRegisters().setLastInit();
        return new Answer("\nПродукт создан!");
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "update - обновит значение элемента коллекции, id которого равен заданному\n";
    }
}
