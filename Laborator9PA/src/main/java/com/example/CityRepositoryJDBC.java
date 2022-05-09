package com.example;

import java.sql.*;

public class CityRepositoryJDBC {
    public void create(String name) throws SQLException
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into cities (id,name) values (city_id_auto.nextval,?)"))
        {   pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
    public void create(String country, String name, int capital, float latitude, float longitude ) throws SQLException
    {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement
                ("insert into cities (id,name,country,capital,latitude,longitude) values (city_id_auto.nextval,?,?,?,?,?)"))
        {   pstmt.setString(1, name);
            pstmt.setString(2, country);
            pstmt.setFloat(3, capital);
            pstmt.setFloat(4, latitude);
            pstmt.setFloat(5, longitude);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id from cities where name='" + name + "'"))
        {return rs.next() ? rs.getInt(1) : null;}
    }
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select name from cities where id=id "))
        {return rs.next() ? rs.getString(1) : null;}
    }
}
