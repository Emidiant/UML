package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.entity.ProductWithAmount;

import java.io.IOException;

public class ProductOrderStatusCell extends ListCell<ProductWithAmount> {

    public AnchorPane anchorPane;
    public ImageView imageView;
    public Label txtName;
    public Label txtDescription;
    public Label txtSize;
    public Label txtColor;
    public Label txtPrice;
    public Label txtTotalPrice;
    public Label txtCount;
    private FXMLLoader fxmlLoader;
    private ProductWithAmount item;
    private Product product;
    private Pair<Integer, Integer> pair_productId_count;

    @Override
    protected void updateItem(ProductWithAmount item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
            setText(null);
        } else {
            this.item = item;
            this.pair_productId_count = item.getValue();
            this.product = item.getKey();
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/productorderstatus.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int id = product.getProductId().indexOf(pair_productId_count.getKey());
            txtName.setText(product.getName());
            txtDescription.setText(product.getSpecification());
            txtPrice.setText(Double.toString(product.getPrice()));
            txtTotalPrice.setText(Double.toString(product.getPrice() * pair_productId_count.getValue()));
            txtColor.setText(Integer.toString(product.getColorIds().get(id)));
            txtSize.setText(Integer.toString(product.getSizeIds().get(id)));
            txtCount.setText(Integer.toString(pair_productId_count.getValue()));
            if (product.getImages().size() != 0) {
                imageView.setImage(product.getImages().get(id));
            } else
                imageView.setImage(null);
            setGraphic(anchorPane);
        }
    }
}
