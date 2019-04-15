package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListCell;
import ru.ifmo.uml.dal.dto.Product;
import ru.ifmo.uml.dal.implementations.ColorImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.ProductOrderImpl;
import ru.ifmo.uml.dal.implementations.SizeImpl;

import java.io.IOException;
import java.util.Map;

public class ProductListForLogistCell extends ListCell<Product> {
    Map<Product, Integer> productCount;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField productName;

    @FXML
    private TextField type;

    @FXML
    private TextField productId;

    @FXML
    private TextField price;

    @FXML
    private TextField size;

    @FXML
    private TextField color;

    @FXML
    private TextField count;

    private FXMLLoader fxmlLoader;

    public void setCountCustomer(Map<Product, Integer> productCount) {
        this.productCount = productCount;
    }

    @Override
    protected void updateItem(Product item, boolean empty) {


        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/productlistforlogistcell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            SizeImpl sizeImpl = new SizeImpl();
            ColorImpl colorImpl = new ColorImpl();

            if (item.getSizeId() != 0) {
                size.setText(sizeImpl.getById(item.getSizeId()).getSizeName());
            } else {
                size.setText("null");
            }

            if (item.getColorId() != 0) {
                color.setText(colorImpl.getById(item.getColorId()).getColorName());
            } else {
                color.setText("null");
            }

            productName.setText(item.getName());
            type.setText(item.getType());
            productId.setText(Integer.toString(item.getProductId()));
            price.setText(Double.toString(item.getPrice()));

            count.setText(Integer.toString(this.productCount.get(item)));

            setGraphic(anchorPane);
        }
    }

}
