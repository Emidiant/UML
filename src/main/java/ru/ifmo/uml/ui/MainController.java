package ru.ifmo.uml.ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.ui.controllers.MainListCell;

import java.io.IOException;

public class MainController {

    private FXMLLoader loader = null;

    @FXML
    private Button loginButton;

    @FXML
    private Button poweroffButton;

    @FXML
    private ListView<Product> listview;

    ObservableList<Product> products = FXCollections.observableArrayList();

    Stage prevStage;
    @FXML
    void initialize() {
        ProductImpl productImpl = new ProductImpl();

        products.clear();
        products.addAll(productImpl.getAll());
        listview.setCellFactory(param -> new MainListCell());
        listview.setItems(products);



        //List<Product> productList = productImpl.getAll();
        //for (Product p : productList){
        //    System.out.println(p);
        //}

        poweroffButton.setOnMouseClicked(e -> System.exit(0));
    }

    public void setStage(Stage stage){
        this.prevStage = stage;
    }


    @FXML
    void logIn(ActionEvent event) {
        MainApp.showLoginPage();
        /*if (loader == null)
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
        stage.show();*/

    }

}

