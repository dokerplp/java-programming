package com.company.javaFX.controllers.modalWindows;

import com.company.javaFX.CSS;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class TextController {
    private final FXMLLoader loader = new FXMLLoader();
    private final InputStream resource = getClass().getResourceAsStream("/fxml/textWindow.fxml");

    @FXML
    private Button ok;

    @FXML
    private Label text;

    @FXML
    private Label title;


    public void show(String text, String title) {

        try {
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            Parent root = loader.load(resource);

            Label lblData = (Label) root.lookup("#text");
            if (lblData != null) lblData.setText(text);

            Label lblData2 = (Label) root.lookup("#title");
            if (lblData2 != null) lblData2.setText(title);

            Scene scene = new Scene(root, 600, 550);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonPressed() {
        CSS.buttonPressed(ok);
    }

    @FXML
    public void buttonReleased() {
        CSS.buttonReleased(ok);
    }

    public void buttonClicked() {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

}
