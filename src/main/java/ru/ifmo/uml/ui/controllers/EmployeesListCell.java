package ru.ifmo.uml.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import ru.ifmo.uml.dal.dto.Administrator;
import ru.ifmo.uml.dal.dto.Order;
import ru.ifmo.uml.dal.implementations.OrderImpl;


import java.io.IOException;

public class EmployeesListCell extends ListCell<Administrator> {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField fullName;

    @FXML
    private TextField employeeID;

    @FXML
    private TextField level;

    private FXMLLoader fxmlLoader;


    @Override
    protected void updateItem(Administrator item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null){
            setText(null);
            setGraphic(null);

        } else {
            if (fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/employeeslistcell.fxml"));
                fxmlLoader.setController(this);
                try{
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            employeeID.setText(Integer.toString(item.getAdministratorId()));
            fullName.setText(item.getSecondName() + " " + item.getFirstName());
            level.setText(item.getLevel());

            setGraphic(anchorPane);
        }
    }

}
