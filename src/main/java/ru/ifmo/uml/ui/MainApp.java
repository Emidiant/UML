package ru.ifmo.uml.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage primaryStage;
    private static FXMLLoader loader = null;
    private static Scene login = null;
    private BorderPane mainLayout;
    private Stage prevStage;


    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;
        primaryStage.getIcons().add(new Image("/image/239.png"));
        primaryStage.setTitle(" Start page");
        //showMainPage();

        FXMLLoader loader = new FXMLLoader();

        Parent root = (Parent) loader.load(MainController.class.getResourceAsStream("/fxml/hellopage.fxml"));
        MainController mainController = loader.getController();
        mainController.setStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void showMainPage() {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(MainController.class.getResourceAsStream("/fxml/hellopage.fxml"));
            MainController mainController = loader.getController();
            mainController.setStage(primaryStage);
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Store");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showLoginPage() {
        if (loader == null)
            loader = new FXMLLoader(MainApp.class.getResource("/fxml/login.fxml"));
        if (login == null) {
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            //stage.getIcons().add(new Image("/image/239.png"));
            primaryStage.setTitle(" Log In");
            Parent root = loader.getRoot();
            LoginController loginController = loader.getController();
            loginController.setStage(primaryStage);
            login = new Scene(root);
        }
        primaryStage.setScene(login);
        //prevStage.close();
        //stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
