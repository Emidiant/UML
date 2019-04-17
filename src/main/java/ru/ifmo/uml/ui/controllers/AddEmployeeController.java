package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.ifmo.uml.dal.dto.*;
import ru.ifmo.uml.dal.implementations.*;

public class AddEmployeeController {

    @FXML
    private TextField error;

    @FXML
    private TextField secondName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField email;

    @FXML
    private TextField login;

    @FXML
    private ComboBox<String> level;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    private Integer id;

    private AdministratorImpl administratorImpl;
    private Administrator administrator;


    public void initialize() {

        administratorImpl = new AdministratorImpl();
        administrator = new Administrator();

        if (level.getItems().isEmpty()) {
            level.getItems().addAll("Admin", "Coordinator", "Logistician");
        }

        btnBack.setOnAction(event -> {
            MainApp.clearAll();
            MainApp.showAdminPage();
        });
    }

    @FXML
    void btnAddClicked() {
        try {
            if (secondName.getText().equals("")
                    || firstName.getText().equals("")
                    || level.getValue().equals("")
                    || email.getText().equals("")
                    || login.getText().equals("")
                    || password.getText().equals(""))
                throw new Exception();

            administrator.setSecondName(secondName.getText());
            administrator.setFirstName(firstName.getText());
            administrator.setLevel(level.getValue());
            administrator.setEmail(email.getText());
            administrator.setLogin(login.getText());
            administrator.setPassword(password.getText());
            administratorImpl.add(administrator);

            error.clear();
            secondName.clear();
            firstName.clear();
            level.valueProperty().set(null);
            email.clear();
            login.clear();
            password.clear();

        } catch (Exception ex) {
            error.setText("Incorrect information");
            System.out.println("Incorrect adding");
        }
    }
}
