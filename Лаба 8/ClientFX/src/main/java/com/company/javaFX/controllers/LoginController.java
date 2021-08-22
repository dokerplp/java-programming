package com.company.javaFX.controllers;

import com.company.javaFX.CSS;
import com.company.javaFX.GUI;
import com.company.javaFX.ModalWindow;
import com.company.javaFX.Language;
import com.company.javaFX.enums.Result;
import com.company.utility.Authorization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable{

    private final Authorization authorization;
    private final GUI GUI;

    public LoginController(Authorization authorization, GUI GUI) {
        this.authorization = authorization;
        this.GUI = GUI;
        loader = new FXMLLoader();
        resource = getClass().getResourceAsStream("/fxml/login.fxml");
        MIN_HEIGHT = 430;
        MIN_WIDTH = 580;
    }

    @FXML
    private Button log;
    @FXML
    private Button sign;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private Label loginLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Menu app;
    @FXML
    private Menu settings;
    @FXML
    private Menu language;
    @FXML
    private MenuItem exit;

    private void error(Result res, Language lang){
        errorLabel.setText(res.getResult(lang));
        errorLabel.setDisable(false);
        errorLabel.setVisible(true);
    }

    @FXML
    private void logInClicked() {
        String LOGIN = login.getText();
        String PASS = pass.getText();

        Result result = authorization.register(LOGIN, PASS, true);

        if (result == Result.SUCCESS) GUI.newMainWindow();
        else ModalWindow.showAlertWindow(Result.CANT_CONNECT, GUI.getLanguage());
    }

    @FXML
    private void signInClicked() {
        String LOGIN = login.getText();
        String PASS = pass.getText();

        authorization.register(LOGIN, PASS, true);
    }

    @FXML
    public void buttonPressed(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getId()){
            case "log":
                CSS.buttonPressed(log);
                break;
            case "sign":
                CSS.buttonPressed(sign);
                break;
        }
    }

    @FXML
    public void buttonReleased(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getId()){
            case "log":
                CSS.buttonReleased(log);
                break;
            case "sign":
                CSS.buttonReleased(sign);
                break;
        }
    }


    @FXML
    private void exitClicked() {
        GUI.getStage().close();
    }

    @FXML
    public void changeLanguage(ActionEvent actionEvent) {
        language(actionEvent, GUI, this);
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        log.setText(bundle.getString("login.log.button"));
        sign.setText(bundle.getString("login.sign.button"));
        loginLabel.setText(bundle.getString("login.login.label"));
        passwordLabel.setText(bundle.getString("login.password.label"));
        welcomeLabel.setText(bundle.getString("login.welcome.label"));
        app.setText(bundle.getString("login.app.menu"));
        settings.setText(bundle.getString("login.settings.menu"));
        language.setText(bundle.getString("login.language.menu"));
        exit.setText(bundle.getString("login.exit.menuItem"));
    }

    @Override
    public void load(Language language, Stage stage) {
        try {
            loader.setController(this);
            loader.setResources(ResourceBundle.getBundle("Login", language.getLocale()));
            Parent root = loader.load(resource);

            Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
            stage.setMinWidth(MIN_WIDTH);
            stage.setMinHeight(MIN_HEIGHT);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Language language) {
        initialize(loader.getLocation(), ResourceBundle.getBundle("Login", language.getLocale()));
    }
}