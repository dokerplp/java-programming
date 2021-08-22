package com.company.javaFX.controllers;

import com.company.javaFX.GUI;
import com.company.javaFX.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.InputStream;

public abstract class Controller {

    protected FXMLLoader loader;
    protected InputStream resource;
    protected double MIN_HEIGHT;
    protected double MIN_WIDTH;


    abstract public void load (Language language, Stage stage);
    abstract public void init (Language language);

    public void language(ActionEvent actionEvent, GUI GUI, Controller controller) {
        MenuItem item = (MenuItem) actionEvent.getTarget();
        switch (item.getId()) {
            case "en":
                GUI.changeLanguage(Language.EN, controller);
                break;
            case "ru":
                GUI.changeLanguage(Language.RU, controller);
                break;
            case "pl":
                GUI.changeLanguage(Language.PL, controller);
                break;
            case "et":
                GUI.changeLanguage(Language.EE, controller);
                break;
        }
    }
}
