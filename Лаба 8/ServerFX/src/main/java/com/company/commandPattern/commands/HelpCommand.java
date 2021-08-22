package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.commandPattern.User;
import com.company.data.io.Answer;
import com.company.data.io.Mail;
import com.company.data.io.RequestResult;

import java.util.HashMap;

/**
 * Command types names of all commands and their clarifications
 */
public class HelpCommand implements Command {

    @Override
    public Answer execute(Mail command, int id) {

        StringBuilder res = new StringBuilder();
        HashMap<String, Command> map = User.getCommandMap();
        map.remove("update_two");
        map.values().stream().map(Command::help).forEach(res::append);
        res.append("exit - завершить программу\n");
        res.append("execute_script - считает и исполнит скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n");
        return new Answer(res.toString(), RequestResult.REQUEST_COMPLETED_SUCCESSFULLY);
    }

    @Override
    public String help() {
        return "help - выводит справку по доступным командам\n";
    }
}
