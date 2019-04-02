package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainController {

    @FXML
    private Button registrButton;

    @FXML
    private Button logInButton;

    @FXML
    private ImageView poweroffButton;

    private FXMLLoader loader;

    Stage prevStage;
    @FXML
    void initialize() {
        poweroffButton.setOnMouseClicked(e -> System.exit(0));
    }


    public void setStage(Stage stage){
        this.prevStage = stage;
    }

    @FXML
    void signIn(ActionEvent event) {

        loader = new FXMLLoader(getClass().getResource("/fxml/logIn.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        Stage stage = (Stage) logInButton.getScene().getWindow();
        stage.getIcons().add(new Image("/image/gamecontroller.png"));
        stage.setTitle(" Log In");
        Parent root = loader.getRoot();
        LogInController logInController = loader.getController();
        logInController.setStage(stage);

        stage.setScene(new Scene(root));
        //prevStage.close();
        stage.show();

    }

    @FXML
    void signUp(ActionEvent event) {

        loader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = (Stage) registrButton.getScene().getWindow();
        stage.getIcons().add(new Image("/image/gamecontroller.png"));
        stage.setTitle(" Sign UP");
        stage.setScene(new Scene(root));
        stage.show();
    }


}

