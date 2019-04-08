package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    @Override
    protected void updateItem(Product item, boolean empty) {
        super.updateItem(item, empty);
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
            if (item.getImageUrl() != null){
                imageView.setImage(new Image("/image/" + item.getImageUrl()));
            }
            else
                imageView.setImage(null);
            setGraphic(anchorPane);
        }
    }
}
