package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.data.io.Answer;
import com.company.data.io.Mail;

import java.util.HashMap;
import java.util.Map;

/**
 * Command types names of all commands and their clarifications
 */
public class HelpCommand implements Command {

    private final Map<String, Command> commandMap;

    public HelpCommand(HashMap<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Execution of this command
     *
     * @return answer
     */
    @Override
    public Answer execute(Mail command) {

        StringBuilder res = new StringBuilder();
        commandMap.values().stream().map(Command::help).forEach(res::append);
        res.append("exit - завершить программу (без сохранения в файл)\n");
        res.append("execute_script - считает и исполнит скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n");
        return new Answer(res.toString());
    }

    /**
     * For help command
     *
     * @return command description
     */
    @Override
    public String help() {
        return "help - выводит справку по доступным командам\n";
    }
}
