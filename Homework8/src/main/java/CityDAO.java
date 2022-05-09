import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import static java.lang.Math.*;

public class CityDAO {
    private String csvFile = "C:\\Users\\spacm\\Documents\\GitHub\\PA\\Homework8\\src\\main\\java\\concap.csv";
    private BufferedReader bufferedReader = null;
    private String line ="";
    private String csvSplitBy = ",";

    public CityDAO(){

    }

    public void createFromCSVFile() throws SQLException{
        String sql = "INSERT INTO Cities (name, latitude,longitude) VALUES(?,?,?)";

        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql));

        bufferedReader = new BufferedReader(new FileReader(csvFile));
        bufferedReader.readLine();

        while ((line = buff))






    }




}

