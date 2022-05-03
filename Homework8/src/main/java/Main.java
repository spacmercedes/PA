
import java.sql.SQLException;

public class Main {
    public static void main(String args[]) {
        try {
            var continents = new ContinentDAO();
            continents.create("Europe");
            continents.create("Africa");
            continents.create("Asia");

            var countries = new CountryDAO();
            int europeId = continents.findByName("Europe");
            int africaId = continents.findByName("Africa");
            countries.create("Romania", "40", europeId);
            countries.create("Ukraine", "380", europeId);

            System.out.println("Countries from " + continents.findById(europeId) + ": " + countries.findCountryFromContinent(europeId));

            Database.closeConnection();

        } catch (SQLException e) {
            System.err.println(e);
//            Database.rollback();
        }
    }
}