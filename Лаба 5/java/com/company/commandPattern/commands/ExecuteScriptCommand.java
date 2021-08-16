package com.company.commandPattern.commands;

import com.company.commandPattern.Command;
import com.company.exceptions.InfinityRecursionException;
import com.company.exceptions.IsFileException;
import com.company.utility.forCommands.FileOperator;
import com.company.utility.forCommands.RequestsQueue;
import com.company.exceptions.CantReadException;
import com.company.exceptions.FileDoesNotExistException;
import com.company.utility.forData.DataBase;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Command execute user's script
 */
public class ExecuteScriptCommand implements Command {

    private final DataBase base; //Queue

    /**
     * ExecuteScriptCommand Constructor
     * @param base - queue
     */
    public ExecuteScriptCommand(DataBase base){
        this.base = base;
    }

    /**
     * Realization of this command
     * @param path - script path
     */
    private void ExecuteScriptRealization(String path){

        RequestsQueue queue = base.getQueue();

        File script = new File(path);
        try { //File checking
            FileOperator.FileExist(script);
            FileOperator.IsFile(script);
            FileOperator.Readable(script);

            queue.setStatus(false); //Program wont write writing invitation

            Scanner requests = new Scanner(script); //Check script
            List<String> list = new ArrayList<>(); //List of commands

            while (requests.hasNext()) list.add(requests.nextLine());
            queue.pushAll(list);

            base.getFields().newRec();

        } catch (FileDoesNotExistException e){
            System.err.println("Скрипт не сработал - файла не существует");
        } catch (IsFileException e){
            System.err.println("Скрипт не сработал - путь ведет к дириктории, а не файлу");
        } catch (CantReadException e){
            System.err.println("Скрипт не сработал - нет прав на чтение файла");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InfinityRecursionException e){
            System.err.println("Глубокая рекурсия");
            queue.clearAll();
        }
    }

    /**
     * Execution of this command
     */
    @Override
    public void execute(String Argument) {
        if (Argument == null) System.err.println("Не хватает аргумета (Адрес скрипта)");
        else ExecuteScriptRealization(Argument);
    }
}
