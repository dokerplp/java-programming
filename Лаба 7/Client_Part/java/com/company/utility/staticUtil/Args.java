package com.company.utility.staticUtil;

/**
 * Utility for getting command name and argument
 */
public class Args {
    /**
     * Set entered command
     *
     * @param line - entered request
     * @return command name
     */
    public static String getCommand(String line) {
        try {
            return line.split(" ")[0];
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Set entered argument
     *
     * @param line - entered request
     * @return argument name
     */
    public static String getArgument(String line) {
        try {
            return line.split(" ")[1];
        } catch (Exception e) {
            return null;
        }
    }
}
