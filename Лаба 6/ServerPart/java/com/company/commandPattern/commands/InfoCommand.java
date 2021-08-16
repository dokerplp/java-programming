package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.memory.Vault;

/**
 * Command types information about collection
 */
public class InfoCommand implements Command {

    private final Vault vault;

    public InfoCommand(Vault vault) {
        this.vault = vault;
    }


    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {
        return new Answer("Салам, сведения о коллекции:\n" +
                "Тип: простой список обьектов\n" +
                "Количество элементов: " + vault.getCollection().size() + "\n" +
                "Последнее сохранение коллекции: " + vault.getRegisters().getLastSave() + "\n" +
                "id последнего элемента: " + vault.getRegisters().getOrgLastId() + "\n" +
                "Последняя инициализация: " + vault.getRegisters().getLastInit());
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "info - выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n";
    }
}
