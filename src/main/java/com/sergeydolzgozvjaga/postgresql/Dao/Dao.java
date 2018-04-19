package com.sergeydolzgozvjaga.postgresql.Dao;

import java.sql.SQLException;

public interface Dao {

    void insertPerson(int id, String name, int age);

    void update(int id, String name, int age);

    void deleteById(int id);

    void deleteByName(String name);

    void displayAll() throws SQLException;

}
