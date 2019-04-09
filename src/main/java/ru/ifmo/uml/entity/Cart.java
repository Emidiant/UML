package ru.ifmo.uml.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<Integer, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public void addProduct(int productId) {
        products.put(productId, products.containsKey(productId) ? products.get(productId) + 1 : 1);
    }

    public void changeAmount(int productId, int amount) {
        products.put(productId, amount);
    }

    public void deleteProduct(int productId) {
        products.remove(productId);
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }


}
