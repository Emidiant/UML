package ru.ifmo.uml.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private FXMLLoader loader;

    @FXML
    private Button loginButton;

    @FXML
    private Button poweroffButton;


    Stage prevStage;
    @FXML
    void initialize() {
        poweroffButton.setOnMouseClicked(e -> System.exit(0));
    }

    public void setStage(Stage stage){
        this.prevStage = stage;
    }

    @FXML
    void logIn(ActionEvent event) {

        loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.getIcons().add(new Image("/image/239.png"));
        stage.setTitle(" Log In");
        Parent root = loader.getRoot();
        LoginController loginController = loader.getController();
        loginController.setStage(stage);

        stage.setScene(new Scene(root));
        prevStage.close();
        stage.show();

    }

}

