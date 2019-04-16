package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.uml.dal.dto.Color;
import ru.ifmo.uml.dal.dto.Product;
import ru.ifmo.uml.dal.dto.Size;
import ru.ifmo.uml.dal.implementations.ColorImpl;
import ru.ifmo.uml.dal.implementations.OrderImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.SizeImpl;
import ru.ifmo.uml.ui.InfoOrderController;
import ru.ifmo.uml.ui.MainApp;


import java.io.IOException;
import java.util.Map;

public class ProductListCell extends ListCell<Product> {

    Map<Product, Integer> productCount;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField productId;

    @FXML
    private TextField name;

    @FXML
    private TextField type;

    @FXML
    private TextField price;

    @FXML
    private TextField count;

    @FXML
    private TextField sizeId;

    @FXML
    private TextField colorId;

    @FXML
    private TextField countCust;

    @FXML
    private TextField countSup;

    @FXML
    private Button importButton;

    private FXMLLoader fxmlLoader;



    public void setCountCustomer(Map<Product, Integer> productCount){
        this.productCount = productCount;
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
            SizeImpl sizeImpl = new SizeImpl();
            ColorImpl colorImpl = new ColorImpl();

            if (item.getSizeId() != 0)
                sizeId.setText(sizeImpl.getById(item.getSizeId()).getSizeName());
            else
                sizeId.setText("null");
            if (item.getColorId() != 0)
                colorId.setText(colorImpl.getById(item.getColorId()).getColorName());
            else
                colorId.setText("null");
            name.setText(item.getName());
            type.setText(item.getType());
            productId.setText(Integer.toString(item.getProductId()));
            price.setText(Double.toString(item.getPrice()));
            count.setText(Integer.toString(item.getCount()));

            countCust.setText(Integer.toString(this.productCount.get(item)));

            importButton.setOnAction(event -> {
                ProductImpl productImpl = new ProductImpl();
                int prevCount = item.getCount();
                try {
                    item.setCount(prevCount + Integer.parseInt(countSup.getText()));
                }catch (Exception e){
                    System.out.println("not integer, aaaa");
                }
                productImpl.update(item);
                count.setText(Integer.toString(item.getCount()));
                countSup.clear();
            });


            setGraphic(anchorPane);
        }
    }
}
