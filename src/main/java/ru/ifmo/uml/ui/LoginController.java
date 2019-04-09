package ru.ifmo.uml.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.implementations.AdministratorImpl;
import ru.ifmo.uml.dal.dto.Administrator;

public class LoginController {
    //TODO back,login btn
    public TextField txtUsername;
    public PasswordField passPassword;
    public Button btnLogin;
    public Button btnBack;
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

        //временно
        txtUsername.setText("morgan");
        passPassword.setText("123654");
    }
    @FXML
    private void btnLoginСlicked(){
        Administrator tempAdministrator = auth.getByLogin(txtUsername.getText());
        if (tempAdministrator.getPassword().equals(passPassword.getText())) {
            administrator = tempAdministrator;
            //в зависимости от уровня открывать coordinator page или logistic page
            MainApp.showCoordPage();

            System.out.println("Login successfully");
        }
    }
    @FXML
    private void btnBackClicked(){
        MainApp.showMainPage();
    }
}
