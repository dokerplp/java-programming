package com.company.utility;

import com.company.exceptions.*;

import java.io.File;

public class FileOperator {

    /**
     * Checks can read file
     *
     * @param file - checking file
     * @throws CantReadException - if you cant read it
     */
    public static void Readable(File file) throws CantReadException {
        if (!file.canRead()) throw new CantReadException();
    }

    /**
     * Checks can write in file
     *
     * @param file - checking file
     * @throws CantWriteException - if you cant write in it
     */
    public static void Writable(File file) throws CantWriteException {
        if (!file.canWrite()) throw new CantWriteException();
    }

    /**
     * Checks if path lead to file
     *
     * @param file - checking file
     * @throws IsFileException - if path doesnot lead to file
     */
    public static void IsFile(File file) throws IsFileException {
        if (!file.isFile()) throw new IsFileException();
    }

    /**
     * Checks if environment variable exist
     *
     * @param path - path prom variable
     * @throws EnvVarDoesNotExistException if variable is empty
     */
    public static void EnvExist(String path) throws EnvVarDoesNotExistException {
        if (path == null) throw new EnvVarDoesNotExistException();
    }

    /**
     * Checks if file exist
     *
     * @param file - checking file
     * @throws FileDoesNotExistException if file doesnot exist
     */
    public static void FileExist(File file) throws FileDoesNotExistException {
        if (!file.exists()) throw new FileDoesNotExistException();
    }

    /**
     * Checking if file is in JSON format
     *
     * @param path - path to file
     * @throws NotJsonException if file is not in JSON format
     */
    public static void IsJSON(String path) throws NotJsonException {
        String[] kostyl = path.split("\\.");
        if (!kostyl[kostyl.length - 1].equals("json")) throw new NotJsonException();
    }
}
