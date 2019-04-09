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

import ru.ifmo.uml.entity.Cart;
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

    private Cart cart;
    ObservableList<Product> products = FXCollections.observableArrayList();

    Stage prevStage;
    @FXML
    void initialize() {
        cart = new Cart();
        ProductRepository productRepository = new ProductRepository();
        productRepository.load();
        products.clear();
        products.addAll(productRepository.getProducts());
        listview.setCellFactory(param -> new MainListCell(){
            @Override
            protected void updateItem(Product item, boolean empty){
                super.updateItem(item,empty);
                this.setCart(cart);
            }
        });
        listview.setItems(products);
        poweroffButton.setOnMouseClicked(e -> System.exit(0));
    }

    public void setStage(Stage stage){
        this.prevStage = stage;
    }


    @FXML
    void logIn(ActionEvent event) {
        MainApp.showLoginPage();


    }

    public void btnCartClicked(ActionEvent actionEvent) {
    }
}

