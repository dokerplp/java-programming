package com.company.utility;

import com.company.exceptions.Exit;

import java.io.Console;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {

    public static final ConsoleHandler CONSOLE = new ConsoleHandler();
    public static final ScannerHandler SCANNER = new ScannerHandler();

    public static class ConsoleHandler {

        private final Console console = System.console();

        private ConsoleHandler() {
        }

        public String readLine() {
            try {
                return console.readLine();
            } catch (Exception e) {
                System.out.println("Я умер...");
                throw new Exit();
            }
        }

        public String readPassword() {
            try {
                char[] pass = console.readPassword();
                return new String(pass);
            } catch (NoSuchElementException e) {
                System.out.println("Я умер...");
                throw new Exit();
            }
        }
    }

    public static class ScannerHandler {

        private final Scanner scanner = new Scanner(System.in);

        private ScannerHandler() {
        }

        public String nextLine() {
            try {
                return scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Я умер...");
                throw new Exit();
            }
        }

        public int nextInt() {
            try {
                return scanner.nextInt();
            } catch (NoSuchElementException e) {
                System.out.println("Я умер...");
                throw new Exit();
            }
        }

    }
}
