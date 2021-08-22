package com.company.commands;

import com.company.exceptions.*;
import com.company.memory.Registers;
import com.company.memory.Vault;
import com.company.utility.staticUtil.FileOperator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Command executes clients scripts
 * This command works without command pattern because runs on client
 */
public class ExecuteScript {

    private final Vault vault;
    private final Registers registers;
    private final String path;

    /**
     * Constructor
     *
     * @param vault     - scripts commands will be added in general requests queue
     * @param registers - need for turning on autoMode
     * @param path      - file address
     */
    public ExecuteScript(Vault vault, Registers registers, String path) {
        this.vault = vault;
        this.registers = registers;
        this.path = path;
        execute();
    }

    /**
     * Command execution
     */
    private void execute() {
        File script = new File(path);

        try { //File checking

            FileOperator.FileExist(script);
            FileOperator.IsFile(script);
            FileOperator.Readable(script);

            registers.modeFalse(); //turn on autoMode

            Scanner requests = new Scanner(script); //Check script
            List<String> list = new ArrayList<>(); //List of commands

            while (requests.hasNext()) list.add(requests.nextLine());
            vault.pushAll(list); //add all commands in queue

            registers.newRecursion(); //new recursions counter

        } catch (FileDoesNotExistException e) {
            System.err.println("Скрипт не сработал - файла не существует");
        } catch (IsFileException e) {
            System.err.println("Скрипт не сработал - путь ведет к дириктории, а не файлу");
        } catch (CantReadException e) {
            System.err.println("Скрипт не сработал - нет прав на чтение файла");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InfinityRecursionException e) {
            vault.clearAll();
        } catch (VaultOverflowException ignored){}
    }
}
