package ru.ifmo.uml.ui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Pair;
import ru.ifmo.uml.entity.Cart;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.ui.MainApp;
import ru.ifmo.uml.ui.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CartController {
    @FXML
    public ListView listviewProducts;
    @FXML
    public Label txtTotal;
    private Double total;
    private Cart cart;
    private ProductRepository productRepository;

    public void setInit(Cart cart, ProductRepository productRepository) {
        this.cart = cart;
        this.productRepository = productRepository;
    }

    @FXML
    void initialize() {
        listviewProducts.setCellFactory(param -> new CartListCell() {
            @Override
            protected void updateItem(Pair<Product, Pair<Integer, Integer>> item, boolean empty) {
                super.updateItem(item, empty);
                super.addListener(obj -> {
                    if (item != null) {
                        if (obj != 0) {
                            cart.changeAmount(item.getValue().getKey(), obj);
                            updateTotal();
                        }
                        else {
                            //TODO Fix exception
                            cart.deleteProduct(item.getValue().getKey());
                            update();
                        }
                    }
                });
            }
        });
    }

    public void updateTotal() {
        total = 0.0;
        Map<Integer, Integer> products = cart.getProducts();
        for (Integer productId : products.keySet()) {
            Product product = productRepository.getProductByProductId(productId);
            total += product.getPrice() * products.get(productId);
        }
        txtTotal.setText(Double.toString(total));
    }

    public void update() {
        total = 0.0;
        List<Pair<Product, Pair<Integer, Integer>>> items = new ArrayList<>();
        Map<Integer, Integer> products = cart.getProducts();
        for (Integer productId : products.keySet()) {
            Product product = productRepository.getProductByProductId(productId);
            items.add(new Pair<>(product, new Pair<>(productId, products.get(productId))));
            total += product.getPrice() * products.get(productId);
        }
        txtTotal.setText(Double.toString(total));
        listviewProducts.setItems(FXCollections.observableArrayList(items));

    }

    public void btnOrderClicked(ActionEvent actionEvent) {
        MainApp.showAddCustomer(cart);

    }

    public void btnBackClicked(ActionEvent actionEvent) {
        MainApp.showMainPage();
    }

}
