package com.sergeydolzgozvjaga.postgresql;

import com.sergeydolzgozvjaga.postgresql.Dao.DaoImpl;

import java.util.logging.Logger;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * @author Sergey Dolgozvjaga
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String args[]) {

        logger.info("start application");
        DaoImpl impl = new DaoImpl();
        impl.displayAll();
        logger.info("exit application");
    }
}
