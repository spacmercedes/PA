import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogUtil {
    public static void save(Catalog catalog, String path) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
        System.out.println("S-a salvat");
    }


    public static Catalog load(String path) throws InvalidCatalogException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = new Catalog();
        try{
            catalog=objectMapper.readValue(new File(path), Catalog.class);
        }
        catch(IOException e){
            System.out.println(e);
        }
        return catalog;
    }}
