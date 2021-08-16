package com.company.utility.staticUtil;

import com.company.exceptions.CantReadException;
import com.company.exceptions.FileDoesNotExistException;
import com.company.exceptions.IsFileException;

import java.io.File;

/**
 * Utility for file checking
 */
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
     * Checks if path lead to file
     *
     * @param file - checking file
     * @throws IsFileException - if path doesnot lead to file
     */
    public static void IsFile(File file) throws IsFileException {
        if (!file.isFile()) throw new IsFileException();
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
}
