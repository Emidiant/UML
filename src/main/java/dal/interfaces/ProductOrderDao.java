package dal.interfaces;

import entity.ProductBooking;
import javafx.util.Pair;

public interface ProductOrderDao extends Dao<ProductBooking, Pair<Integer, Integer>> {
    //Other methods not from Dao class
}
