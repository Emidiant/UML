package ru.ifmo.uml.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage primaryStage;
    private static FXMLLoader loader = null;
    private static Scene login = null;
    private static Scene admin = null;
    private static Scene info = null;
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

    public static void showCoordPage() {
        //if (loader == null)
            loader = new FXMLLoader(MainApp.class.getResource("/fxml/coordpage.fxml"));
        if (admin == null) {
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            //stage.getIcons().add(new Image("/image/239.png"));
            primaryStage.setTitle("Сoordinator");
            Parent root = loader.getRoot();
            CoordPageController coordPageController = loader.getController();
            coordPageController.setStage(primaryStage);
            admin = new Scene(root);
        }
        primaryStage.setScene(admin);
    }

    public static void showInfoOrderPage(Integer id) {
        //if (loader == null)
            loader = new FXMLLoader(MainApp.class.getResource("/fxml/infoorderpage.fxml"));
        if (info == null) {
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            primaryStage.setTitle("Information");
            Parent root = loader.getRoot();
            InfoOrderController infoOrderController = loader.getController();
            infoOrderController.setId(id);//todo поздно доходит, надо как-то разумно исправить, в прошлый раз закрывала костылём
            infoOrderController.setStage(primaryStage);


            info = new Scene(root);
        }
        primaryStage.setScene(info);
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
