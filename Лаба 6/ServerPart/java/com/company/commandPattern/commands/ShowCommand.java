package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Collection types all fields of each element
 */
public class ShowCommand implements Command {

    private final Vault vault;

    public ShowCommand(Vault vault) {
        this.vault = vault;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {

        vault.getCollection().sort((o1, o2) -> {
            try (
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos)
            ) {
                int o1Len;
                int o2Len;
                oos.writeObject(o1);
                o1Len = baos.size();
                baos.reset();
                oos.writeObject(o2);
                o2Len = baos.size();
                return o1Len - o2Len;
            } catch (IOException e) {
                return 0;
            }
        });

        return new Answer("show command", vault.getCollection());
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении\n";
    }

}
