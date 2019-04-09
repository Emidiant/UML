package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.uml.entity.Order;
import ru.ifmo.uml.entity.Product;

import java.io.IOException;
import java.util.ArrayList;

public class ProductListCell extends ListCell<Product> {

    ArrayList<Integer> countCustomer = new ArrayList<>();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label productId;

    @FXML
    private Label name;

    @FXML
    private Label type;

    @FXML
    private Label price;

    @FXML
    private Label count;

    @FXML
    private Label countCust;


    private FXMLLoader fxmlLoader;


    public void setCountCustomer(ArrayList countCustomer){
        this.countCustomer = countCustomer;
    }

    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null){
            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/productlistcell.fxml"));
                fxmlLoader.setController(this);
                try{
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            name.setText(item.getName());
            type.setText(item.getType());
            productId.setText(Integer.toString(item.getProductId()));
            price.setText(Double.toString(item.getPrice()));
            count.setText(Integer.toString(item.getCount()));
            //countCust.setText(Integer.toString(this.countCustomer.get(1)));

            setGraphic(anchorPane);
        }
    }
}
