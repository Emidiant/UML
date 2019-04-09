package ru.ifmo.uml.dal.interfaces;


import javafx.util.Pair;
import ru.ifmo.uml.dal.dto.ProductOrder;

import java.util.List;

public interface ProductOrderDao extends Dao<ProductOrder, Pair<Integer, Integer>> {
    //Other methods not from Dao class

    List<ProductOrder> getByOrderId(Order order);
}
