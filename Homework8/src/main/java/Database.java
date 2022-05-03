
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Database {
    private static final String URL = "jdbc:sqlite:WorldCities.sqlite";
    private static final String USER = "victoria";
    private static final String PASSWORD = "password";
    private static Connection connection = null;
    private List<String> savepoints = new ArrayList<>();

    private Database() {
    }

    public static void setConnection(Connection connection) {
        Database.connection = connection;
    }

    public static Connection getConnection() {
        createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
//            System.out.println("Database opened");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closeConnection()  {
        try {
            if (connection != null)
                connection.close();

            System.out.println("Database closed");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void printSavepoints() {
        for (String save : savepoints)
            System.out.println("Savepoint: " + save);
    }

    public void savepoint() throws SQLException {
        Statement statement = connection.createStatement();

        String save = "SAVE " + savepoints.size();

        savepoints.add(save);
        String sql = "SAVEPOINT " + save + ";";
        statement.executeUpdate(sql);
        System.out.println("Savepoint " + save + " created!");

    }

    public void savepoint(String savepoint) throws SQLException {
        Statement statement = connection.createStatement();

        String save = savepoint;
        savepoints.add(save);
        String sql = "SAVEPOINT " + save + ";";
        statement.executeUpdate(sql);
        System.out.println("Savepoint " + save + " created!");

    }

    public void rollback(String savepoint) throws SQLException {
        Statement statement = connection.createStatement();

        boolean realised = true;
        for (String save : savepoints)
            if (save.equals(savepoint)) {
                String sql = "ROLLBACK TO " + savepoint + ";";
                statement.executeUpdate(sql);

                realised = false;
            }

        if (realised)
            System.out.println("Savepoint " + savepoint + " does not exist!");
        else {
            System.out.println("Rollback to " + savepoint + " executed!");
        }
    }

}
