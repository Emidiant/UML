package ru.ifmo.uml.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import ru.ifmo.uml.dal.dto.Administrator;
import ru.ifmo.uml.dal.implementations.AdministratorImpl;
import ru.ifmo.uml.ui.MainApp;


public class AdminPageController {

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnAdd;

    @FXML
    private ListView<Administrator> employeesListView;

    ObservableList<Administrator> employeesList = FXCollections.observableArrayList();

    public void initialize(){
        createTabEmployeesView();

        btnAdd.setOnAction(event -> {
            MainApp.showAddEmployee();
        });

        btnLogout.setOnAction(event -> {
            MainApp.clearAll();
            MainApp.showMainPage();
        });
    }

    public void createTabEmployeesView() {
        AdministratorImpl employeesImpl = new AdministratorImpl();

        employeesList.clear();
        employeesList.addAll(employeesImpl.getAll());

        employeesListView.setCellFactory(param -> {
                    EmployeesListCell employeesListCell = new EmployeesListCell();

                    employeesListCell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                        if (event.getButton() == MouseButton.PRIMARY && (!employeesListCell.isEmpty())) {
                            Administrator item = employeesListCell.getItem();
                            showDetails(item);
                        }
                    });
                    return employeesListCell;
                }
        );
        employeesListView.setItems(employeesList);

    }

    public void showDetails(Administrator item){
        MainApp.showInfoEmployeePage(item.getAdministratorId());
    }
}
