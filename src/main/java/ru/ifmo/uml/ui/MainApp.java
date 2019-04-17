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
import ru.ifmo.uml.ui.controllers.StatusController;

import java.io.IOException;

public class MainApp extends Application {
    private static Stage primaryStage;
    private static FXMLLoader loaderLogin = null;
    private static FXMLLoader loaderAdmin = null;
    private static FXMLLoader loaderCoord = null;
    private static FXMLLoader loaderLogist = null;
    private static FXMLLoader loaderInfoEmployee = null;
    private static FXMLLoader loaderInfoOrder = null;
    private static FXMLLoader loaderInfoDelivery = null;
    private static FXMLLoader loaderCart = null;
    private static FXMLLoader loaderStatus = null;
    private static Scene login = null;
    private static Scene admin = null;
    private static Scene info = null;
    private static Scene cart = null;
    private static Scene status = null;
    private static Cart cartItem;
    private static ProductRepository productRepository;
    private BorderPane mainLayout;
    private Stage prevStage;

    private static Integer delOrderById = null;


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
            mainController.setInit(cartItem, productRepository);
            mainController.update();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Store");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearInfo() {
        info = null;
        loaderInfoDelivery = null;
        loaderInfoEmployee = null;
        loaderInfoOrder = null;
    }

    public static void clearAll() {
        loaderAdmin = null;
        loaderInfoEmployee = null;
        loaderCoord = null;
        loaderInfoOrder = null;
        loaderLogist = null;
        loaderInfoDelivery = null;
        admin = null;
        info = null;
    }

    public static void showAdminPage() {
        if (loaderAdmin == null) {
            loaderAdmin = new FXMLLoader(MainApp.class.getResource("/fxml/adminpage.fxml"));
        }
        primaryStage.setTitle("Administrator");
        if (admin == null) {
            try {
                loaderAdmin.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            Parent root = loaderAdmin.getRoot();
            admin = new Scene(root);
        }
        primaryStage.setScene(admin);
    }

    public static void showCoordPage() {
        if (loaderCoord == null) {
            loaderCoord = new FXMLLoader(MainApp.class.getResource("/fxml/coordpage.fxml"));
        }
        primaryStage.setTitle("Сoordinator");
        if (admin == null) {
            try {
                loaderCoord.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            //stage.getIcons().add(new Image("/image/239.png"));
            Parent root = loaderCoord.getRoot();
            CoordPageController coordPageController = loaderCoord.getController();
            coordPageController.setStage(primaryStage);
            admin = new Scene(root);
        }
        primaryStage.setScene(admin);
    }

    public static void showLogistPage() {
        if (loaderLogist == null)
            loaderLogist = new FXMLLoader(MainApp.class.getResource("/fxml/logistpage.fxml"));
        primaryStage.setTitle("Logistician");
        if (admin == null) {
            try {
                loaderLogist.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            Parent root = loaderLogist.getRoot();
            admin = new Scene(root);
        }
        primaryStage.setScene(admin);
    }

    public static void showInfoEmployeePage(Integer id) {
        if (loaderInfoEmployee == null)
            loaderInfoEmployee = new FXMLLoader(MainApp.class.getResource("/fxml/infoemployeepage.fxml"));
        primaryStage.setTitle("Information");
        if (info == null) {
            try {
                loaderInfoEmployee.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            Parent root = loaderInfoEmployee.getRoot();
            info = new Scene(root);
        }
        InfoEmployeeController infoEmployeeController = loaderInfoEmployee.getController();
        infoEmployeeController.setId(id);
        primaryStage.setScene(info);
    }

    public static void showInfoOrderPage(Integer id) {
        if (loaderInfoOrder == null)
            loaderInfoOrder = new FXMLLoader(MainApp.class.getResource("/fxml/infoorderpage.fxml"));
        primaryStage.setTitle("Information");
        if (info == null) {
            //info = null;
            try {
                loaderInfoOrder.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            Parent root = loaderInfoOrder.getRoot();
            info = new Scene(root);
        }
        InfoOrderController infoOrderController = loaderInfoOrder.getController();
        infoOrderController.setId(id);
        infoOrderController.setStage(primaryStage);
        primaryStage.setScene(info);
    }

    public static void showInfoDeliveryPage(Integer id) {
        if (loaderInfoDelivery == null)
            loaderInfoDelivery = new FXMLLoader(MainApp.class.getResource("/fxml/infodeliverypage.fxml"));
        primaryStage.setTitle("Information");
        if (info == null) {
            try {
                loaderInfoDelivery.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            Parent root = loaderInfoDelivery.getRoot();
            info = new Scene(root);
        }
        InfoDeliveryController infoDeliveryController = loaderInfoDelivery.getController();
        infoDeliveryController.setId(id);
        primaryStage.setScene(info);
    }

    public static void showLoginPage() {
        primaryStage.setTitle("Log In");
        changeScene(loaderLogin, login, "/fxml/login.fxml");

    }

    public static void showAddProduct() {

        primaryStage.setTitle("Add Product");
        changeScene(loaderLogin,login,"/fxml/addproduct.fxml");

    }

    public static void showAddEmployee() {
        primaryStage.setTitle("Add Employee");
        changeScene(loaderLogin,login,"/fxml/addemployee.fxml");
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
            cartController.setInit(cartItem, productRepository);
            cart = new Scene(root);
        }
        CartController cartController = loaderCart.getController();
        cartController.update();
        primaryStage.setScene(cart);
        //primaryStage.setTitle("Cart");
        //changeScene(loaderCart,cart,"/fxml/cart.fxml");
    }

    private static void changeScene(FXMLLoader loader, Scene scene, String url) {
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

    public static void showOrderStatus() {
        if (loaderStatus == null)
            loaderStatus = new FXMLLoader(MainApp.class.getResource("/fxml/status.fxml"));
        if (status == null) {
            try {
                loaderStatus.load();
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

            Parent root = loaderStatus.getRoot();
            StatusController statusController = loaderStatus.getController();
            statusController.setOrderManager(new OrderManager(productRepository));
            status = new Scene(root);
        }
        primaryStage.setScene(status);
    }

    public static void main(String[] args) {
        cartItem = new Cart();
        productRepository = new ProductRepository();
        productRepository.load();
        launch(args);
    }
}
