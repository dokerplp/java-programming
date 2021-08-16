package com.company.commandPattern;

import com.company.exceptions.IncorrectDataException;
import com.company.memory.Vault;
import com.company.utility.FileOperator;
import com.company.utility.Log;
import com.company.utility.json.JSONBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Command saves collection in json file
 */
public class SaveCommand implements Runnable {

    private final Vault vault;
    private final Log log = new Log();

    public SaveCommand(Vault vault) {
        this.vault = vault;
    }


    /**
     * Realization of this command
     */
    private void SaveRealization() throws IncorrectDataException, FileNotFoundException {

        String path = CheckFile();

        if (path != null) {
            File file = new File(path);
            JSONBuilder builder = new JSONBuilder();

            PrintWriter pw = new PrintWriter(file);
            String line = builder.packCollection(vault.getCollection());
            pw.print(line);
            pw.close();
        } else throw new IncorrectDataException();
    }

    /**
     * This method checks if the environment variable is working.
     * If it is method saves collection in that file
     *
     * @return path to saving file
     */
    private String CheckFile() {
        try {
            String path = System.getenv("LAB");
            FileOperator.EnvExist(path);
            File DJEYSON = new File(path);
            FileOperator.FileExist(DJEYSON);
            FileOperator.IsFile(DJEYSON);
            FileOperator.IsJSON(path);
            FileOperator.Writable(DJEYSON);
            return path;
        } catch (Exception e) {
            System.err.println("Возникли проблемы с исходным файлом");
            log.exception(e);
        }
        return null;
    }

    /**
     * Execution of this command
     */
    public String execute() {
        if (!vault.getRegisters().isSavesAllowed()) {
            System.err.println("Сохранения запрещены!");
            return null;
        }
        try {
            SaveRealization();
            vault.getRegisters().setLastSave();
            System.out.println("Сохранено!");
        } catch (Exception e) {
            System.err.println("Сохранение не состоялось");
            log.exception(e);
        }
        return null;
    }

    @Override
    public void run() {
        System.err.println("Сохраняю коллекцию...");
        execute();
    }
}
