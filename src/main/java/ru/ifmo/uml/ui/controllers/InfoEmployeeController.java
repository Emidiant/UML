package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.ifmo.uml.dal.dto.*;
import ru.ifmo.uml.dal.implementations.*;
import ru.ifmo.uml.ui.MainApp;

public class InfoEmployeeController {

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
    private TextField employeeID;

    @FXML
    private ComboBox<String> level;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnBack;

    private Integer id;

    private AdministratorImpl administratorImpl;
    private Administrator administrator;


    public void initialize() {

        btnBack.setOnAction(event -> {
            MainApp.clearInfo();
            MainApp.showAdminPage();
        });
    }

    public void setId(Integer id) {
        this.id = id;
        createInfo(id);
    }

    public void createInfo(Integer id) {
        administratorImpl = new AdministratorImpl();
        administrator = administratorImpl.getById(id);

        if (level.getItems().isEmpty())
            level.getItems().addAll("Admin", "Coordinator", "Logistician");

        employeeID.setText(Integer.toString(administrator.getAdministratorId()));
        secondName.setText(administrator.getSecondName());
        firstName.setText(administrator.getFirstName());
        level.setValue(administrator.getLevel());
        email.setText(administrator.getEmail());
        login.setText(administrator.getLogin());
        password.setText(administrator.getPassword());
    }

    @FXML
    void btnDeleteClicked() {
        administratorImpl.delete(administrator);
        MainApp.clearAll();
        MainApp.showAdminPage();
    }

    @FXML
    void btnUpdateClicked() {
        try {
            if (secondName.getText().equals("")
                    || firstName.getText().equals("")
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
            administratorImpl.update(administrator);
            MainApp.clearAll();
            MainApp.showAdminPage();
        } catch (Exception ex) {
            error.setText("Incorrect information");
            System.out.println("Incorrect updating");
        }
    }
}
