package com.company.javaFX.controllers.modalWindows;

import com.company.javaFX.CSS;
import com.company.javaFX.Language;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class CommandWithArgumentsController implements Initializable {
    private final FXMLLoader loader = new FXMLLoader();
    private final InputStream resource = getClass().getResourceAsStream("/fxml/commandWithArgsWindow.fxml");

    @FXML
    private Button ok;
    @FXML
    private Button execute;

    @FXML
    private Label result;
    @FXML
    private Label value;

    @FXML
    private TextField argument;


    public void show(Language language, String command) {

        try {
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);

            loader.setResources(ResourceBundle.getBundle("Modal", language.getLocale()));

            Parent root = loader.load(resource);

            Label lblData = (Label) root.lookup("#command");
            if (lblData !=null) lblData.setText(command);

            Scene scene = new Scene(root, 600, 220);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buttonPressed(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getId()){
            case "ok":
                CSS.buttonPressed(ok);
                break;
            case "execute":
                CSS.buttonPressed(execute);
                break;
        }
    }

    @FXML
    public void buttonReleased(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getId()){
            case "ok":
                CSS.buttonReleased(ok);
                break;
            case "execute":
                CSS.buttonReleased(execute);
                break;
        }
    }

    @FXML
    public void okClicked() {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void executeClicked(){
        result.setText("Some server answer at " + argument.getText());
        result.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        value.setText(resourceBundle.getString("modal.value.label"));
    }

}
