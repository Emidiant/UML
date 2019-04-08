package ru.ifmo.uml.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {
    //TODO back,login btn
    public TextField txtUsername;
    public PasswordField passPassword;
    public Button btnLogin;
    public Button btnBack;
    public ImageView imgBig;
    Stage prevStage;
    public void setStage(Stage stage){
        this.prevStage = stage;
    }
    @FXML
    void initialize(){

    }
}
