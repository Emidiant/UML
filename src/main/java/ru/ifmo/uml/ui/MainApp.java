package ru.ifmo.uml.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane mainLayout;
    private Stage prevStage;


    @Override
    public void start(Stage stage) throws IOException {

        this.primaryStage = stage;
        //this.primaryStage.getIcons().add(new Image("/image/gamecontroller.png"));
        this.primaryStage.setTitle(" Start page");
        //showMainPage();

        FXMLLoader loader = new FXMLLoader();

        Parent root = (Parent) loader.load(MainController.class.getResourceAsStream("/fxml/hellopage.fxml"));
        MainController mainController = loader.getController();
        mainController.setStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void showMainPage() {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(MainController.class.getResourceAsStream("/fxml/hellopage.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}
