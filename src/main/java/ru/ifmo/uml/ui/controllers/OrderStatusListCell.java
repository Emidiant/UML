package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import ru.ifmo.uml.entity.Product;

import java.io.IOException;

public class OrderStatusListCell extends ListCell<Pair<Product, Pair<Integer, Integer>>> {
    public ImageView imageview;
    public Label txtName;
    public Label txtSize;
    public Label txtColor;
    public Label txtAmount;
    public Label txtPrice;
    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(Pair<Product, Pair<Integer, Integer>> item, boolean empty) {
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/orderstatuslistcell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Product product = item.getKey();
            int productId = item.getValue().getKey();
            int amount = item.getValue().getValue();
            int id = product.getProductId().indexOf(productId);
            imageview.setImage(product.getImages().get(id));
            txtName.setText(product.getName());
            txtSize.setText(Integer.toString(product.getSizeIds().get(id)));
            txtColor.setText(Integer.toString(product.getColorIds().get(id)));
            txtPrice.setText(Double.toString(amount*product.getPrice()));

        }
    }
}
