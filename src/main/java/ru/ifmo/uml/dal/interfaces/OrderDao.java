package ru.ifmo.uml.dal.interfaces;


import ru.ifmo.uml.dal.dto.Order;

public interface OrderDao extends Dao<Order, Integer> {
    //Other methods not from Dao class
    Integer getLastOrderId();
}
