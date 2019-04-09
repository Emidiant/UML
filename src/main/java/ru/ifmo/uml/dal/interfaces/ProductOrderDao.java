package ru.ifmo.uml.dal.interfaces;

import ru.ifmo.uml.entity.Order;
import ru.ifmo.uml.entity.ProductOrder;
import javafx.util.Pair;

import java.util.List;

public interface ProductOrderDao extends Dao<ProductOrder, Pair<Integer, Integer>> {
    //Other methods not from Dao class

    List<ProductOrder> getByOrderId(Order order);
}
