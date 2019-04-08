package ru.ifmo.uml.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.dto.Administrator;
import ru.ifmo.uml.dal.implementations.AdministratorImpl;


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
    }
    @FXML
    private void btnLoginClicked(){
        Administrator tempAdministrator = auth.getByLogin(txtUsername.getText());
        if (tempAdministrator.getPassword().equals(passPassword.getText())) {
            administrator = tempAdministrator;
            System.out.println("Login successfully");
        }
    }
    @FXML
    private void btnBackClicked(){
        MainApp.showMainPage();
    }
}
