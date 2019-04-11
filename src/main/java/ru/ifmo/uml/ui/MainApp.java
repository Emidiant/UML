package ru.ifmo.uml.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import ru.ifmo.uml.entity.Cart;
import ru.ifmo.uml.ui.controllers.CartController;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage primaryStage;
    private static FXMLLoader loaderLogin = null;
    private static FXMLLoader loaderCoord = null;
    private static FXMLLoader loaderInfo = null;
    private static FXMLLoader loaderCart = null;
    private static Scene login = null;
    private static Scene admin = null;
    private static Scene info = null;
    private static Scene cart = null;
    private static Cart cartItem;
    private static ProductRepository productRepository;
    private BorderPane mainLayout;
    private Stage prevStage;


    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;
        primaryStage.getIcons().add(new Image("/image/239.png"));
        primaryStage.setTitle(" Start page");
        showMainPage();

        /*FXMLLoader loader = new FXMLLoader();

        Parent root = (Parent) loader.load(MainController.class.getResourceAsStream("/fxml/hellopage.fxml"));
        MainController mainController = loader.getController();
        mainController.setStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);*/
        primaryStage.show();


    }

    public static void showMainPage() {
        FXMLLoader loader = new FXMLLoader();
        try {
            Parent root = loader.load(MainController.class.getResourceAsStream("/fxml/hellopage.fxml"));
            MainController mainController = loader.getController();
            mainController.setStage(primaryStage);
            mainController.setInit(cartItem,productRepository);
            mainController.update();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Store");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showCoordPage() {
        if (loaderCoord == null)
            loaderCoord = new FXMLLoader(MainApp.class.getResource("/fxml/coordpage.fxml"));
        if (admin == null) {
            try {
                loaderCoord.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            //stage.getIcons().add(new Image("/image/239.png"));
            primaryStage.setTitle("Сoordinator");
            Parent root = loaderCoord.getRoot();
            CoordPageController coordPageController = loaderCoord.getController();
            coordPageController.setStage(primaryStage);
            admin = new Scene(root);
        }
        primaryStage.setScene(admin);
    }

    public static void showInfoOrderPage(Integer id) {
        if (loaderInfo == null)
            loaderInfo = new FXMLLoader(MainApp.class.getResource("/fxml/infoorderpage.fxml"));
        if (info == null) {
            try {
                loaderInfo.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            primaryStage.setTitle("Information");
            Parent root = loaderInfo.getRoot();
            InfoOrderController infoOrderController = loaderInfo.getController();
            infoOrderController.setId(id);
            infoOrderController.setStage(primaryStage);


            info = new Scene(root);
        }
        primaryStage.setScene(info);
    }

    public static void showLoginPage() {
        /*if (loaderLogin == null)
            loaderLogin = new FXMLLoader(MainApp.class.getResource("/fxml/login.fxml"));
        if (login == null) {
            try {
                loaderLogin.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            //stage.getIcons().add(new Image("/image/239.png"));
            primaryStage.setTitle(" Log In");
            Parent root = loaderLogin.getRoot();
            LoginController loginController = loaderLogin.getController();
            loginController.setStage(primaryStage);
            login = new Scene(root);
        }
        primaryStage.setScene(login);*/
        primaryStage.setTitle("Log In");
        changeScene(loaderLogin,login,"/fxml/login.fxml");

    }

    public static void showCartPage() {
        if (loaderCart == null)
            loaderCart = new FXMLLoader(MainApp.class.getResource("/fxml/cart.fxml"));
        if (cart == null) {
            try {
                loaderCart.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            primaryStage.setTitle("Cart");
            Parent root = loaderCart.getRoot();
            CartController cartController = loaderCart.getController();
            cartController.setInit(cartItem,productRepository);
            cart = new Scene(root);
        }
        CartController cartController = loaderCart.getController();
        cartController.update();
        primaryStage.setScene(cart);
        //primaryStage.setTitle("Cart");
        //changeScene(loaderCart,cart,"/fxml/cart.fxml");
    }
    private static void changeScene(FXMLLoader loader, Scene scene, String url){
        if (loader == null)
            loader = new FXMLLoader(MainApp.class.getResource(url));
        if (scene == null) {
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            //LoginController loginController = loader.getController();
            //loginController.setStage(primaryStage);
            scene = new Scene(root);
        }
        primaryStage.setScene(scene);
    }
    public static void main(String[] args) {
        cartItem = new Cart();
        productRepository = new ProductRepository();
        productRepository.load();
        launch(args);
    }
}
