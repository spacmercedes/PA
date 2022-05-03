
import java.sql.*;

public class CountryDAO {

    public void create(String name, String code, Integer continent) throws SQLException {

        String sql = "INSERT INTO Countries (name, code, continent) VALUES(?,?,?)";

        if(findByName(name) != null)
            System.out.println("Country "+ name +" already exists!");
        else{
            try (Connection connection = Database.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, name);
                preparedStatement.setString(2, code);
                preparedStatement.setInt(3, continent);
                preparedStatement.executeUpdate();
            }
        }
    }

    public Integer findByName(String name) throws SQLException {

        String sql = "SELECT id FROM Countries WHERE name='" + name + "';";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            return rs.next() ? rs.getInt("id") : null;
        }
    }

    public String findById(int id) throws SQLException {

        String sql = "SELECT name FROM Countries WHERE id=" + id + ";";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            return rs.next() ? rs.getString("name") : null;
        }
    }

    public String findCountryFromContinent(int id) throws SQLException {

        String sql = "SELECT name FROM Countries WHERE continent=" + id + ";";
        StringBuilder sb = new StringBuilder();

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while(rs.next()){
                sb.append(rs.getString("name") + " ");
            }
            return sb.toString();
        }
    }
}