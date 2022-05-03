import java.sql.*;
public class ContinentDAO {

    public void create(String name) throws SQLException {

        String sql = "INSERT INTO Continents (name) VALUES(?)";

        if(findByName(name) != null)
            System.out.println("Continent " + name +  " already exists!");
        else{
            try (Connection connection = Database.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, name);
                System.out.println(preparedStatement.executeUpdate());

            }
        }

    }

    public Integer findByName(String name) throws SQLException {

        String sql = "SELECT id FROM Continents WHERE name='" + name + "';";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            return rs.next() ? rs.getInt("id") : null;
        }
    }

    public String findById(int id) throws SQLException {

        String sql = "SELECT name FROM Continents WHERE id=" + id + ";";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            return rs.next() ? rs.getString("name") : null;
        }
    }
}