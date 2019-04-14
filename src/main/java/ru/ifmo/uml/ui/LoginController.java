package ru.ifmo.uml.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
    private Stage prevStage;
    public void setStage(Stage stage){
        this.prevStage = stage;
    }
    @FXML
    void initialize(){
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
                    } else if (tempAdministrator.getLevel().equals("logist")) {
                        comboBox.setValue("Logistician");
                    } else if (tempAdministrator.getLevel().equals("low")) {
                        comboBox.setValue("Coordinator");
                    } else if (tempAdministrator.getLevel().equals("admin")) {
                        comboBox.setDisable(false);
                    }
                });

        //временнo
        //COORDINATOR
        txtUsername.setText("morgan");
        //LOGICIAN
        //txtUsername.setText("iivanov");
        //GOD
        //txtUsername.setText("admin");
        passPassword.setText("123654");

    }

    @FXML
    private void btnBackClicked(){
        MainApp.showMainPage();
    }

    public void btnLoginClicked(ActionEvent actionEvent) {
        Administrator tempAdministrator = auth.getByLogin(txtUsername.getText());
        try {
            if (tempAdministrator.getPassword().equals(passPassword.getText())) {
                administrator = tempAdministrator;
                //в зависимости от уровня открывать coordinator page или logistic page
                if (administrator.getLevel().equals("admin")) {
                    try {
                        if (comboBox.getValue().equals("Admin")) {
                            //TODO: showAdminPage
                        } else if (comboBox.getValue().equals("Coordinator")) {
                            MainApp.showCoordPage();
                        } else if (comboBox.getValue().equals("Logistician")) {
                            MainApp.showLogistPage();
                        }
                    } catch (NullPointerException e) {
                        incorrectData.setText("Choose mode");
                    }

                } else if (administrator.getLevel().equals("low")) {
                    MainApp.showCoordPage();
                } else if (administrator.getLevel().equals("logist")) {
                    MainApp.showLogistPage();
                }

            }
        } catch (NullPointerException e) {
            incorrectData.setText("Incorect name or password");
        }
    }
}
