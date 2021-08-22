package com.company.javaFX;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ApplePicture {

    private final GraphicsContext gc;

    public ApplePicture(Stage stage) {

        Group root = new Group();
        Canvas canvas = new Canvas(500, 500);
        this.gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public void draw(Color color, int x, int y, int size) {

        gc.setFill(color);

        int i = size / 5;

        gc.beginPath();
        gc.arc(7 * i + x, 3 * i + y, i * Math.sqrt(5), i * Math.sqrt(5), 30, 120);
        gc.arc(3 * i + x, 3 * i + y, i * Math.sqrt(5), i * Math.sqrt(5), 30, 120);
        gc.arc(5 * i + x, 5 * i + y, 5 * i, 5 * i, 144, 90);
        gc.arc(3.5 * i + x, 7.5 * i + y, 1.5 * i * Math.sqrt(2), 1.5 * i * Math.sqrt(2), 225, 90);
        gc.arc(6.5 * i + x, 7.5 * i + y, 1.5 * i * Math.sqrt(2), 1.5 * i * Math.sqrt(2), 225, 90);
        gc.arc(5 * i + x, 5 * i + y, 5 * i, 5 * i, 306, 90);
        gc.closePath();
        gc.fill();

        gc.beginPath();
        gc.setFill(Color.BLACK);
        gc.moveTo(5 * i + x, 2 * i + y);
        gc.lineTo(5.5 * i + x, -1 * i + y);
        gc.lineTo(4.5 * i + x, -1 * i + y);
        gc.lineTo(5 * i + x, 2 * i + y);
        gc.fill();
        gc.closePath();
    }
}
