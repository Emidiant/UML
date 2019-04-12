package ru.ifmo.uml.ui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import ru.ifmo.uml.entity.Product;
import ru.ifmo.uml.ui.ProductRepository;
import ru.ifmo.uml.utils.Listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class CartListCell extends ListCell<Pair<Product, Pair<Integer, Integer>>> {
    @FXML
    public Label txtName;
    @FXML
    public Label txtDescription;
    @FXML
    public Label txtSize;
    @FXML
    public Label txtColor;
    @FXML
    public Label txtPrice;
    @FXML
    public Spinner spinner;
    @FXML
    public Label txtTotalPrice;
    @FXML
    public ImageView imageView;
    @FXML
    public AnchorPane anchorPane;
    private Pair<Product, Pair<Integer, Integer>> item;
    private Product product;
    private Pair<Integer, Integer> productId;
    private List<Listener<Integer>> listeners;
    private FXMLLoader fxmlLoader;

    public void addListener(Listener<Integer> listener) {
        if (listeners == null) listeners = new ArrayList<>();
        listeners.add(listener);
    }

    public void deleteListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(int count) {

        for (Listener<Integer> listener : listeners) {
            listener.notifyListener(count);
        }

    }

    @Override
    protected void updateItem(Pair<Product, Pair<Integer, Integer>> item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.item = item;
            this.productId = item.getValue();
            this.product = item.getKey();
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cartlistcell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int id = product.getProductId().indexOf(productId.getKey());
            txtName.setText(product.getName());
            txtDescription.setText(product.getSpecification());
            txtPrice.setText(Double.toString(product.getPrice()));
            txtColor.setText(Integer.toString(product.getColorIds().get(id)));
            txtSize.setText(Integer.toString(product.getSizeIds().get(id)));
            //TODO get size, color
            spinner.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, productId.getValue()));
            spinner.valueProperty().addListener((ChangeListener<Integer>)
                    (observable, oldValue, newValue) ->
                            txtTotalPrice.setText(Double.toString(product.getPrice() * newValue)));
            spinner.valueProperty().addListener((ChangeListener<Integer>) (observable, oldValue, newValue) ->
                    notifyListeners(newValue));
            if (product.getImages().size() != 0) {
                imageView.setImage(product.getImages().get(id));
            } else
                imageView.setImage(null);
            setGraphic(anchorPane);
        }
    }

}
