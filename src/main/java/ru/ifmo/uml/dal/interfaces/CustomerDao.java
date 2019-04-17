package ru.ifmo.uml.dal.interfaces;


import ru.ifmo.uml.dal.dto.Customer;

public interface CustomerDao extends Dao<Customer, Integer> {
    //Other methods not from Dao class
    Integer getLastCustomerId();

    Integer getCustomerIdByEmail(String email);
}
