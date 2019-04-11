package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.uml.entity.Cart;
import ru.ifmo.uml.entity.Product;


import java.io.IOException;


public class MainListCell extends ListCell<Product> {
    @FXML
    public Label cost;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label name;
    @FXML
    private Label info;
    @FXML
    private ImageView imageView;
    private FXMLLoader fxmlLoader;
    private int count = 0;
    private Product item;
    private Cart cart;
    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);
        this.item = item;
        if (empty || item == null){
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainlistcell.fxml"));
                fxmlLoader.setController(this);
                try{
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            name.setText(item.getName());
            info.setText(item.getSpecification());
            cost.setText(Double.toString(item.getPrice()));
            //TODO add maybe color, maybe size
            if (item.getImages().size() != 0){
                imageView.setImage(item.getImages().get(count));
            }
            else
                imageView.setImage(null);
            setGraphic(anchorPane);
        }
    }
    @FXML
    public void imgViewClicked(){
        if (item.getImages().size() != 0){
            count = (count + 1) % item.getImages().size();
            imageView.setImage(item.getImages().get(count));
        }
        else
            imageView.setImage(null);
    }
    @FXML
    public void btnAddToCartPressed(){
        if (cart != null){
            cart.addProduct(item.getProductId().get(0));
            System.out.println(cart.getProducts().toString());
        }
        else
            System.err.println("Error mainlistcell");
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
