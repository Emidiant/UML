package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.uml.dal.dto.Product;


import java.io.IOException;

public class StockListCell extends ListCell<Product> {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label count;

    @FXML
    private Label type;

    @FXML
    private Label price;

    @FXML
    private Label article;

    @FXML
    private Label productId;

    @FXML
    private Label productName;

    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null){
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/stocklistcell.fxml"));
                fxmlLoader.setController(this);
                try{
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            productName.setText(item.getName());
            count.setText(Integer.toString(item.getCount()));
            productId.setText(Integer.toString(item.getProductId()));
            price.setText(Double.toString(item.getPrice()));
            type.setText(item.getType());
            article.setText(Integer.toString(item.getArticle()));

            setGraphic(anchorPane);
        }
    }
}
