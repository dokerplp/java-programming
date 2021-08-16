package com.company.run;

import com.company.javaFX.GUI;
import javafx.application.Application;
import javafx.stage.Stage;


public class Client extends Application {

    @Override
    public void start(Stage stage) {
        (new GUI(getParameters().getRaw(), stage)).newLoginWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
