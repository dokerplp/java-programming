package com.company.javaFX.controllers.modalWindows;

import com.company.data.*;
import com.company.data.io.Mail;
import com.company.javaFX.CSS;
import com.company.javaFX.ModalWindow;
import com.company.javaFX.Language;
import com.company.utility.Authorization;
import com.company.utility.DataValidation;
import com.company.utility.ServerHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    private final FXMLLoader loader = new FXMLLoader();
    private final InputStream resource = getClass().getResourceAsStream("/fxml/addWindow.fxml");

    private boolean addressCreation;
    private final Socket socket;
    private final String command;
    private final Authorization authorization;

    public AddController(Socket socket, String command, Authorization authorization) {
        this.socket = socket;
        this.command = command;
        this.authorization = authorization;
    }

    @FXML
    private Button create;
    @FXML
    private Button cancel;

    @FXML
    private Label product;
    @FXML
    private Label prodName;
    @FXML
    private Label price;
    @FXML
    private Label part;
    @FXML
    private Label cost;
    @FXML
    private Label UOM;
    @FXML
    private Label orgName;
    @FXML
    private Label EC;
    @FXML
    private Label orgType;
    @FXML
    private Label street;
    @FXML
    private Label ZIP;

    @FXML
    private Label coordinates;
    @FXML
    private Label organization;
    @FXML
    private Label address;

    @FXML
    private TextField prodNameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField partField;
    @FXML
    private TextField costField;
    @FXML
    private TextField XField;
    @FXML
    private TextField YField;
    @FXML
    private ChoiceBox<UnitOfMeasure> UOMField;
    private final ObservableList<UnitOfMeasure> UOMoptions = FXCollections.observableArrayList(UnitOfMeasure.LITERS, UnitOfMeasure.CENTIMETERS, UnitOfMeasure.METERS);
    @FXML
    private TextField orgNameField;
    @FXML
    private TextField ECField;
    @FXML
    private ChoiceBox<OrganizationType> orgTypeField;
    private final ObservableList<OrganizationType> OrgOptions = FXCollections.observableArrayList(OrganizationType.GOVERNMENT,OrganizationType.PUBLIC,OrganizationType.PRIVATE_LIMITED_COMPANY);
    @FXML
    private TextField streetField;
    @FXML
    private TextField ZIPField;

    @FXML
    private CheckBox addressCheckBox;
    @FXML
    private GridPane addressField;

    //TODO cancel button summon 2 error windows and 2nd throws exit exception

    public void show(Language language) {
        try {
            loader.setController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            loader.setResources(ResourceBundle.getBundle("Modal", language.getLocale()));
            Parent root = loader.load(resource);

            UOMField.setValue(UnitOfMeasure.LITERS);
            UOMField.setItems(UOMoptions);

            orgTypeField.setValue(OrganizationType.PUBLIC);
            orgTypeField.setItems(OrgOptions);

            Scene scene = new Scene(root, 600, 800);

            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonPressed(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getId()){
            case "create":
                CSS.buttonPressed(create);
                break;
            case "cancel":
                CSS.buttonPressed(cancel);
                break;
        }
    }

    @FXML
    private void buttonReleased(MouseEvent mouseEvent) {
        switch (((Button) mouseEvent.getSource()).getId()){
            case "create":
                CSS.buttonReleased(create);
                break;
            case "cancel":
                CSS.buttonReleased(cancel);
                break;
        }
    }

    @FXML
    private void createClicked(){
        StringBuilder err = new StringBuilder();

        Address address = null;
        Coordinates coordinates = null;
        Organization organization = null;
        Product product = null;

        try {
            if (addressCreation){
                Object o = DataValidation.validateAddress(streetField.getText(), ZIPField.getText());
                if (o instanceof String) err.append((String) o);
                else if (o instanceof Address) address = (Address) o;
            }

            Object o = DataValidation.validateCoordinates(XField.getText(),YField.getText());
            if (o instanceof String) err.append((String) o);
            else if (o instanceof Coordinates) coordinates = (Coordinates) o;

            o = DataValidation.validateOrganization(orgNameField.getText(), ECField.getText(), orgTypeField.getValue(), address);
            if (o instanceof String) err.append((String) o);
            else if (o instanceof Organization) organization = (Organization) o;

            o = DataValidation.validateProduct(prodNameField.getText(), coordinates, priceField.getText(), partField.getText(),UOMField.getValue(), costField.getText(), organization);
            if (o instanceof String) err.append((String) o);
            else if (o instanceof Product) product = (Product) o;

            if (err.length() == 0) {
                ServerHandler.send(new Mail(command, product, authorization.getLOGIN(), authorization.getPASS()), socket);
                Stage stage = (Stage) create.getScene().getWindow();
                stage.close();
                System.out.println("Sent");
            }
            else ModalWindow.showTextWindow(err.toString(), "Error");
        } catch (NullPointerException e){
            err.append("Please fill all fields");
            ModalWindow.showTextWindow(err.toString(), "Error");
        }

    }

    @FXML
    private void cancelClicked(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product.setText(resourceBundle.getString("modal.product.label"));
        prodName.setText(resourceBundle.getString("modal.name.label"));
        price.setText(resourceBundle.getString("modal.price.label"));
        part.setText(resourceBundle.getString("modal.partNumber.label"));
        cost.setText(resourceBundle.getString("modal.manufactureCost.label"));
        UOM.setText(resourceBundle.getString("modal.unitOfMeasure.label"));
        orgName.setText(resourceBundle.getString("modal.name.label"));
        EC.setText(resourceBundle.getString("modal.employeeCount.label"));
        orgType.setText(resourceBundle.getString("modal.organizationType.label"));
        street.setText(resourceBundle.getString("modal.street.label"));
        ZIP.setText(resourceBundle.getString("modal.ZIP.label"));
        coordinates.setText(resourceBundle.getString("modal.coordinates.label"));
        organization.setText(resourceBundle.getString("modal.organization.label"));
        address.setText(resourceBundle.getString("modal.address.label"));
    }

    public void addressSwitch() {
        if (addressCheckBox.isSelected()) {
            addressField.setVisible(true);
            addressField.setDisable(false);
            addressCreation = true;
        }
        else {
            addressField.setVisible(false);
            addressField.setDisable(true);
            addressCreation = false;
        }
    }
}
