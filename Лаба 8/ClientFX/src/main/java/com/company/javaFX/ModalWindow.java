package com.company.javaFX;

import com.company.javaFX.controllers.modalWindows.*;
import com.company.javaFX.enums.Result;
import com.company.utility.Authorization;

import java.net.Socket;

public class ModalWindow {

    public static void showAddWindow(Language language, Socket socket, String command, Authorization authorization){
        (new AddController(socket, command, authorization)).show(language);
    }

    public static void showAlertWindow(Result result, Language language){
        (new AlertController()).show(result, language);
    }

    public static void showTextWindow(String text, String command){
        (new TextController()).show(text,command);
    }

    public static void showCommandWithArgumentWindow(Language language, String command){
        (new CommandWithArgumentsController()).show(language, command);
    }
}
