package com.sergeydolzgozvjaga.postgresql.Dao;

import java.sql.*;
import java.util.logging.Logger;

public class DaoImpl implements Dao {

    private final Logger logger = Logger.getLogger(DaoImpl.class.getName());

    // JDBC URL, username and password of MySQL server
    private final String url = "jdbc:postgresql://localhost:5432/persondb" +
            "?useSSL=false" +
            "&useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private final String user = "postgres";
    private final String password = "postgres";

    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * Method open connection to MySQL server
     *
     * @param url      - url value of database connect
     * @param user     - user value to connect to database
     * @param password - password value to connect to database
     * */
    private void getConnection(String url, String user, String  password) throws SQLException{
        logger.info("start getConnection(..)");
        con = DriverManager.getConnection(url, user, password);
        logger.info("exit getConnection(..)");
    }

    /**
     * Method close connection with MySQL server
     * */
    private void closeConnection(){
        logger.info("start closeConnection()");
            try {
                con.close();
            } catch (SQLException e) {
                logger.warning(e.getMessage());
            }
        logger.info("exit closeConnection()");
        }

    /**
     * Method insert new Person to database
     *
     * @param id   - id value in database
     * @param name - name value in database
     * @param age  - age value in database
     * */
    @Override
    public void insertPerson(int id, String name, int age) {
        logger.info("start insertPerson(..)");
        // query to database
        String query = "insert into persons values(?,?,?)";
        try {
            getConnection(url, user, password);
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            int i = preparedStatement.executeUpdate();
            if (i != 0){
                logger.info("inserted");
            } else {
                logger.info("not inserted");
            }
        }catch (SQLException e){logger.warning(e.getMessage());
        }finally { closeConnection(); }
        logger.info("exit insertPerson(..)");
    }

    /**
     * Method update values in database
     *
     * @param id   - id value in database
     * @param name - name value in database
     * @param age  - age value in database
     * */
    @Override
    public void update(int id, String name, int age) {
        logger.info("start update(..)");
        // query to database
        String query = "update persons set name=?, age=? where id=?";
        try{
            getConnection(url, user, password);
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            int i = preparedStatement.executeUpdate();
            if (i != 0){
                logger.info("update");
            } else {
                logger.info("not update");
            }
        }catch (SQLException e){logger.warning(e.getMessage());
        }finally { closeConnection(); }
        logger.info("exit update(..)");
    }

    /**
     * Method delete value from database by id
     *
     * @param id - id value in database
     * */
    @Override
    public void deleteById(int id) {
        logger.info("start deleteById(..)");
        // query to database
        String query = "delete from persons where id=?";
        try{
            getConnection(url, user, password);
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            if (i != 0) {
                logger.info("deleted");
            } else {
                logger.info("not deleted");
            }
        }catch (SQLException e){logger.warning(e.getMessage());
        }finally { closeConnection(); }
        logger.info("exit deleteById(..)");
    }

    /**
     * Method delete value from database by name
     *
     * @param name - id value in database
     * */
    @Override
    public void deleteByName(String name) {
        logger.info("start deleteByName(..)");
        // query to database
        String query = "delete from persons where name=?";
        try{
            getConnection(url, user, password);
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            int i = preparedStatement.executeUpdate();
            if (i != 0) {
                logger.info("deleted");
            } else {
                logger.info("not deleted");
            }
        }catch (SQLException e){logger.warning(e.getMessage());
        } finally { closeConnection(); }
        logger.info("exit deleteByName(..)");
    }

    /**
     * Method show all values in database
     * */
    @Override
    public void displayAll(){
        logger.info("start displayAll()");
        // query to database
        String query = "SELECT * FROM persons";
        try {
            getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) +
                        "  " + resultSet.getString(2) +
                        "  " + resultSet.getInt(3));
            }
        } catch (SQLException sqlEx) {
            logger.warning(sqlEx.getMessage());
        } finally { closeConnection(); }
        logger.info("exit displayAll()");
    }
}