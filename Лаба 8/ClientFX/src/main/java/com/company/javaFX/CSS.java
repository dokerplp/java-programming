package com.company.javaFX;

import javafx.scene.control.Button;

public class CSS {
    public static void buttonPressed(Button button){
        button.setStyle("-fx-border-radius: 8; -fx-background-color: #fff; -fx-border-color: #000;");
    }
    public static void buttonReleased(Button button){
        button.setStyle("-fx-border-radius: 8; -fx-background-color: #fff; -fx-border-color: #ccc;");
    }
}