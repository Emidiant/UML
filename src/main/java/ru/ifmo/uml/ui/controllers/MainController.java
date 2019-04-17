package ru.ifmo.uml.ui.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import ru.ifmo.uml.entity.Cart;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.ui.MainApp;

public class MainController {

    private FXMLLoader loader = null;

    @FXML
    private Button loginButton;

    @FXML
    private Button poweroffButton;

    @FXML
    private ListView<Product> listview;
    private ProductRepository productRepository;
    private Cart cart;
    ObservableList<Product> products = FXCollections.observableArrayList();

    Stage prevStage;
    @FXML
    void initialize() {


        listview.setCellFactory(param -> new MainListCell(){
            @Override
            protected void updateItem(Product item, boolean empty){
                super.updateItem(item,empty);
                this.setCart(cart);
            }
        });

        poweroffButton.setOnMouseClicked(e -> System.exit(0));
    }
    public void setInit(Cart cart, ProductRepository productRepository){
        this.cart = cart;
        this.productRepository = productRepository;
    }
    public void update(){
        products.clear();
        products.addAll(productRepository.getProducts());
        listview.setItems(products);
    }
    public void setStage(Stage stage){
        this.prevStage = stage;
    }


    @FXML
    void logIn(ActionEvent event) {
        MainApp.showLoginPage();
    }

    public void btnCartClicked(ActionEvent actionEvent) {
        MainApp.showCartPage();
    }

    public void btnCheckOrderClicked(ActionEvent actionEvent) {
        MainApp.showOrderStatus();
    }
}

