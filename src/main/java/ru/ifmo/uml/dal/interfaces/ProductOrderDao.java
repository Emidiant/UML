package ru.ifmo.uml.dal.interfaces;

import ru.ifmo.uml.entity.ProductOrder;
import javafx.util.Pair;

public interface ProductOrderDao extends Dao<ProductOrder, Pair<Integer, Integer>> {
    //Other methods not from Dao class
}
