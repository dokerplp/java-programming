package com.company.javaFX;


import com.company.javaFX.controllers.Controller;
import com.company.javaFX.controllers.LoginController;

import com.company.javaFX.controllers.MainController;
import com.company.javaFX.enums.WindowTypes;
import com.company.utility.Authorization;
import javafx.stage.Stage;

import java.util.List;

public class GUI {

    private Language language = Language.EN;
    public Language getLanguage() {
        return language;
    }

    private final Stage stage;
    public Stage getStage() {
        return stage;
    }

    private Controller login;
    private Controller main;
    private ModalWindow modal = new ModalWindow();

    public GUI(List<String> args, Stage stage) {
        this.stage = stage;
        Authorization authorization = new Authorization(args);
        this.login = new LoginController(authorization, this);
        this.main = new MainController(authorization, this);
    }

    public void newLoginWindow(){
        login.load(language, stage);
    }

    public void newMainWindow(){
        main.load(language, stage);
    }

    public void changeLanguage(Language language, Controller controller){
        this.language = language;
        controller.init(language);
    }
}
