package ru.ifmo.uml.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.ifmo.uml.dal.dto.Color;
import ru.ifmo.uml.dal.dto.Product;
import ru.ifmo.uml.dal.dto.Size;
import ru.ifmo.uml.dal.implementations.ColorImpl;
import ru.ifmo.uml.dal.implementations.ProductImpl;
import ru.ifmo.uml.dal.implementations.SizeImpl;
import ru.ifmo.uml.ui.MainApp;

import java.util.List;

public class AddProductController {

    @FXML
    public TextField txtProductName;

    @FXML
    public TextField txtPrice;

    @FXML
    public TextField txtArticle;

    @FXML
    public TextField txtType;

    @FXML
    public TextField txtImage;

    @FXML
    public Label txtWarning;

    @FXML
    public TextField txtSpecification;

    @FXML
    public ComboBox<Integer> countBox;

    @FXML
    public ComboBox<String> colorBox;

    @FXML
    public ComboBox<String> sizeBox;

    public Button btnSave;
    public Button btnBack;
    private Stage prevStage;

    public void setStage(Stage stage){
        this.prevStage = stage;
    }
    @FXML
    void initialize(){

        colorBoxCreate();
    }

    @FXML
    private void btnBackClicked(){
        MainApp.clearInfo();
        MainApp.showCoordPage();
    }
    @FXML
    public void btnSaveClicked(ActionEvent actionEvent) {
        Product product = new Product();
        ProductImpl productImpl = new ProductImpl();
        SizeImpl sizeImpl = new SizeImpl();
        ColorImpl colorImpl = new ColorImpl();

        try{
            product.setArticle(Integer.parseInt(txtArticle.getText()));
            product.setImageUrl(txtImage.getText());
            product.setName(txtProductName.getText());
            product.setPrice(Double.parseDouble(txtPrice.getText()));
            product.setColorId(colorImpl.getByName(colorBox.getValue()));
            product.setSizeId(sizeImpl.getByName(sizeBox.getValue()));
            product.setCount(countBox.getValue());
            product.setType(txtType.getText());
            product.setSpecification(txtSpecification.getText());
            productImpl.add(product);
        }
        catch (Exception e){
            txtWarning.setText("You must fill in all fields!");
        }
    }

    public void colorBoxCreate(){
        ColorImpl colorImpl = new ColorImpl();

        List<Color> colorList = colorImpl.getAll();
        for (Color c : colorList){
            colorBox.getItems().add(c.getColorName());
        }

        SizeImpl sizeImpl = new SizeImpl();
        List<Size> sizeList = sizeImpl.getAll();
        for (Size s : sizeList){
            sizeBox.getItems().add(s.getSizeName());
        }
        for (int i = 0; i <= 200; i++){
            countBox.getItems().add(i);
        }
    }


}
