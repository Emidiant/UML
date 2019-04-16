package ru.ifmo.uml.entity;

import javafx.util.Pair;

public class ProductWithAmount extends Pair<Product, Pair<Integer,Integer>> {
    public ProductWithAmount(Product key, int productId, int amount) {
        super(key, new Pair<>(productId,amount));
    }
    public Product getProduct(){
        return this.getKey();
    }
    public int getProductId(){
        return this.getValue().getKey();
    }
    public int getAmount(){
        return this.getValue().getValue();
    }
}
