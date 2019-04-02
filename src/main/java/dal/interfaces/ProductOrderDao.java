package dal.interfaces;

import entity.ProductOrder;
import javafx.util.Pair;

public interface ProductOrderDao extends Dao<ProductOrder, Pair<Integer, Integer>> {
    //Other methods not from Dao class
}
