package com.company.commandPattern.commands;

import com.company.commandPattern.Command;

/**
 * Command types names of all commands and their clarifications
 */
public class HelpCommand implements Command {

    /**
     * Realization of this command
     */
    private void HelpRealization(){
        System.out.println(
                "Салам, мои комманды:\n" +
                        "help - выводит справку по доступным командам\n" +
                        "info - выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                        "show - выводит в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                        "add - добавит новый элемент в коллекцию\n" + //add(element)
                        "update - обновит значение элемента коллекции, id которого равен заданному\n" + //(update(id, element))
                        "remove_by_id - удалит элемент из коллекции по его id\n" + //remove_by_id(id)
                        "clear - очистит коллекцию\n" +
                        "save - сохранит коллекцию в файл\n" +
                        "execute_script - считает и исполнит скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" + //execute_script(file_name)
                        "exit - завершит программу (без сохранения в файл)\n" +
                        "add_if_max - добавит новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" + //dd_if_max(element)
                        "shuffle - перемешает элементы коллекции в случайном порядке\n" +
                        "remove_greater - удалит из коллекции все элементы, превышающие заданный\n" + //remove_greater(element)
                        "average_of_price - выведет среднее значение поля price для всех элементов коллекции\n" +
                        "count_by_manufacture_cost - выведет количество элементов, значение поля manufactureCost которых равно заданному\n" + //count_by_manufacture_cost(manufactureCost)
                        "count_less_than_manufacture_cost - выведет количество элементов, значение поля manufactureCost которых меньше заданного\n" //count_less_than_manufacture_cost(manufactureCost)
        );
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument != null) System.err.println("Эта комманда не требует никаих аргуметов так-то :)");
        HelpRealization();
    }
}
