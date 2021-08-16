package com.company.javaFX.controllers.modalWindows;

import com.company.javaFX.CSS;
import com.company.javaFX.Language;
import com.company.javaFX.enums.Result;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AlertController implements Initializable {

    private final FXMLLoader loader = new FXMLLoader();
    private final InputStream resource = getClass().getResourceAsStream("/fxml/alertWindow.fxml");

    @FXML
    private Button ok;

    @FXML
    private Label error;


    public void show(Result result, Language language) {

        try {
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            loader.setResources(ResourceBundle.getBundle("Modal", language.getLocale()));

            Parent root = loader.load(resource);

            Label lblData = (Label) root.lookup("#alert");
            if (lblData!=null) lblData.setText(result.getI18N());

            Scene scene = new Scene(root, 500, 210);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error.setText(resourceBundle.getString("modal.alert.label"));
    }
}
