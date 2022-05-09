package com.example;

import java.sql.*;

public class CountryRepositoryJDBC {
    public void create(String name, int idContinent) throws SQLException
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into countries (id,name,continent) values (country_id_auto.nextval,?,?)"))
        {   pstmt.setString(1, name);
            pstmt.setInt(2, idContinent);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from countries where name='" + name + "'"))
        {return rs.next() ? rs.getInt(1) : null;}
    }
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select name from countries where id=id "))
        {return rs.next() ? rs.getString(1) : null;}
    }
}

