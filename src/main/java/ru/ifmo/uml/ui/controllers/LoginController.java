package ru.ifmo.uml.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import ru.ifmo.uml.dal.implementations.AdministratorImpl;
import ru.ifmo.uml.dal.dto.Administrator;

public class LoginController {
    //TODO back,login btn
    public TextField txtUsername;
    public PasswordField passPassword;
    public ComboBox<String> comboBox;
    public Button btnLogin;
    public Button btnBack;
    public TextField incorrectData;
    public ImageView imgBig;
    public Administrator administrator = null;
    private AdministratorImpl auth;

    @FXML
    void initialize() {
        auth = new AdministratorImpl();

        if (comboBox.getItems().isEmpty()) {
            comboBox.getItems().addAll("Admin", "Coordinator", "Logistician");
        }

        txtUsername.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    Administrator tempAdministrator = auth.getByLogin(txtUsername.getText());
                    if (tempAdministrator == null) {
                        comboBox.setValue(null);
                        comboBox.setDisable(true);
                    } else if (tempAdministrator.getLevel().equals("Logistician")) {
                        comboBox.setValue("Logistician");
                    } else if (tempAdministrator.getLevel().equals("Coordinator")) {
                        comboBox.setValue("Coordinator");
                    } else if (tempAdministrator.getLevel().equals("Admin")) {
                        comboBox.setDisable(false);
                    }
                });

        btnBack.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                btnBackClicked();
            }
        });

        btnLogin.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                btnLoginClicked(new ActionEvent());
            }
        });


        //временнo
        //COORDINATOR
        //txtUsername.setText("morgan");
        //LOGICIAN
        //txtUsername.setText("iivanov");
        //GOD
        txtUsername.setText("emidiant");
        passPassword.setText("123654");

    }

    @FXML
    private void btnBackClicked() {
        MainApp.showMainPage();
    }

    public void btnLoginClicked(ActionEvent actionEvent) {
        Administrator tempAdministrator = auth.getByLogin(txtUsername.getText());
        try {
            if (tempAdministrator.getPassword().equals(passPassword.getText())) {
                administrator = tempAdministrator;
                //в зависимости от уровня открывать coordinator page или logistic page
                if (administrator.getLevel().equals("Admin")) {
                    try {
                        if (comboBox.getValue().equals("Admin")) {
                            MainApp.showAdminPage();
                        } else if (comboBox.getValue().equals("Coordinator")) {
                            MainApp.showCoordPage();
                        } else if (comboBox.getValue().equals("Logistician")) {
                            MainApp.showLogistPage();
                        }
                    } catch (NullPointerException e) {
                        incorrectData.setText("Choose mode");
                    }

                } else if (administrator.getLevel().equals("Coordinator")) {
                    MainApp.showCoordPage();
                } else if (administrator.getLevel().equals("Logistician")) {
                    MainApp.showLogistPage();
                }

            }
        } catch (NullPointerException e) {
            incorrectData.setText("Incorrect name or password");
        }
    }
}
