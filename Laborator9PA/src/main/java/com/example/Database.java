package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static  Connection connection=null;

    private Database() {
    }
    public static void createConnection()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","student","STUDENT");
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();}
    }
    public static Connection getConnection() throws SQLException {
        if(connection==null) {
            createConnection();
        }
        return connection;
    }
    public static void closeConnection() throws SQLException
    {
        connection.close();
    }
    public static void commit() throws SQLException {
        connection.commit();
    }
}