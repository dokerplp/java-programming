package com.company.javaFX.controllers;

import com.company.data.*;
import com.company.javaFX.GUI;
import com.company.javaFX.Language;
import com.company.utility.Authorization;
import com.company.utility.CommandCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {

    private final CommandCreator creator;

    private GUI GUI;
    public MainController(Authorization authorization, GUI GUI) {
        this.GUI = GUI;
        this.creator = new CommandCreator(authorization);
        loader = new FXMLLoader();
        resource = getClass().getResourceAsStream("/fxml/main.fxml");
        MIN_HEIGHT = 500;
        MIN_WIDTH = 580;
    }

    @FXML
    private Menu app;
    @FXML
    private Menu settings;
    @FXML
    private Menu language;
    @FXML
    private MenuItem exit;
    @FXML
    private Menu command;
    @FXML
    private Tab table;
    @FXML
    private Tab anim;

    @FXML
    private TableColumn<Product, Integer> prodId;
    @FXML
    private TableColumn<Product, String> prodName;
    @FXML
    private TableColumn<Coordinates, Float> X;
    @FXML
    private TableColumn<Coordinates, Integer> Y;
    @FXML
    private TableColumn<Product, ZonedDateTime> creation;
    @FXML
    private TableColumn<Product, Long> price;
    @FXML
    private TableColumn<Product, String> partNumber;
    @FXML
    private TableColumn<Product, Float> manufactureCost;
    @FXML
    private TableColumn<Product, UnitOfMeasure> UOM;
    @FXML
    private TableColumn<Organization, Integer> orgId;
    @FXML
    private TableColumn<Organization, String> orgName;
    @FXML
    private TableColumn<Organization, Long> EC;
    @FXML
    private TableColumn<Organization, OrganizationType> orgType;
    @FXML
    private TableColumn<Address,String> street;
    @FXML
    private TableColumn<Address, String> ZIP;
    @FXML
    private TableColumn<Product, Coordinates> coordinates;
    @FXML
    private TableColumn<Product, Organization> organization;
    @FXML
    private TableColumn<Organization, Address> address;

    @Override
    public void load(Language language, Stage stage) {
        try {
            loader.setController(this);
            loader.setResources(ResourceBundle.getBundle("Main", language.getLocale()));
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

    public void init(Language language) {
        initialize(loader.getLocation(), ResourceBundle.getBundle("Main", language.getLocale()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        app.setText(resourceBundle.getString("main.app.menu"));
        settings.setText(resourceBundle.getString("main.settings.menu"));
        language.setText(resourceBundle.getString("main.language.menu"));
        exit.setText(resourceBundle.getString("main.exit.menuItem"));
        command.setText(resourceBundle.getString("main.commands.menu"));
        table.setText(resourceBundle.getString("main.table.tab"));
        anim.setText(resourceBundle.getString("main.anim.tab"));
        prodName.setText(resourceBundle.getString("main.name.table"));
        creation.setText(resourceBundle.getString("main.creation.table"));
        price.setText(resourceBundle.getString("main.price.table"));
        partNumber.setText(resourceBundle.getString("main.partNumber.table"));
        manufactureCost.setText(resourceBundle.getString("main.manufactureCost.table"));
        UOM.setText(resourceBundle.getString("main.unitOfMeasure.table"));
        orgName.setText(resourceBundle.getString("main.name.table"));
        EC.setText(resourceBundle.getString("main.employee.table"));
        orgType.setText(resourceBundle.getString("main.orgType.table"));
        street.setText(resourceBundle.getString("main.street.table"));
        ZIP.setText(resourceBundle.getString("main.ZIP.table"));
        coordinates.setText(resourceBundle.getString("main.coordinates.table"));
        organization.setText(resourceBundle.getString("main.organization.table"));
        address.setText(resourceBundle.getString("main.address.table"));
    }

    @FXML
    public void commandExecute(ActionEvent actionEvent) {

        MenuItem item = (MenuItem) actionEvent.getTarget();
        creator.mailCreate(item.getId(), GUI.getLanguage());

    }

    @FXML
    public void changeLanguage(ActionEvent actionEvent) {
        language(actionEvent, GUI, this);
    }

    @FXML
    private void exitClicked() {
        GUI.getStage().close();
    }

    private String help = "add - добавит новый элемент в коллекцию\n" +
            "clear - очистит коллекцию\n" +
            "show - выводит в стандартный поток вывода все элементы \n" +
            "коллекции в строковом представлении\n" +
            "remove_by_id - удалит элемент из коллекции по его id\n" +
            "count_by_manufacture_cost - выведет количество элементов, \n" +
            "значение поля manufactureCost которых равно заданному\n" +
            "add_if_max - добавит новый элемент в коллекцию, если его \n" +
            "значение превышает значение наибольшего элемента этой \n" +
            "коллекции\n" +
            "remove_greater - удалит из коллекции все элементы, \n" +
            "превышающие заданный\n" +
            "average_of_price - выведет среднее значение поля price \n" +
            "для всех элементов коллекции\n" +
            "help - выводит справку по доступным командам\n" +
            "update - обновит значение элемента коллекции, id которого \n" +
            "равен заданному\n" +
            "count_less_than_manufacture_cost - выведет количество \n" +
            "элементов, значение поля manufactureCost которых меньше заданного\n" +
            "shuffle - перемешает элементы коллекции в случайном порядке\n" +
            "info - выводит в стандартный поток вывода информацию о \n" +
            "коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
            "exit - завершить программу\n" +
            "execute_script - считает и исполнит скрипт из указанного \n" +
            "файла. В скрипте содержатся команды в таком же виде, в котором \n" +
            "их вводит пользователь в интерактивном режиме.";
}
