package ru.ifmo.uml.dal.interfaces;


import ru.ifmo.uml.dal.dto.Administrator;

public interface AdministratorDao extends Dao<Administrator, Integer> {
    //Other methods not from Dao class
    Administrator getByLogin(String login);
}
