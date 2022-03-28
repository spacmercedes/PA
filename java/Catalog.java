import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;import java.awt.Desktop;
import java.io.*;

public class Catalog implements Serializable {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("List")
    private List<Item> list;
    @JsonProperty("Path")
    private String path;

    public Catalog() {
        list = new ArrayList<>();
    }

    public Catalog(String path, String name) {
        this.name=name;
        try {
            list = new ArrayList<>();


            if (Files.exists(Paths.get(path))) {
                Files.delete(Paths.get(path));
            }

            this.path = path;
        } catch (Exception e) {
            System.err.println(" !!!!!!!!!!!!!Unexpected error");
        }
    }


    public void setList(List<Item> lst) {
        this.list = list;
    }

    public List<Item> getList() {
        return this.list;
    }

    public void add(Item i) {
        try {
            this.list.add(i);
        } catch (NullPointerException e) {
            System.err.println("Null pointer exception " + e);
        }
    }

    public void list() {
        for (Item i : list) {
            System.out.println(i.getTitle());
        }
    }


    public String getPath() {
        return this.path;
    }

    public Item findById(String id) {
        return (Item) list.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
//        for (Item i : lst) {
//              System.out.println(i.getId());
//            }
    }



    public void saveToFile(Catalog catalog, String path) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        try{
        objectMapper.writeValue(new File(path), catalog);
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("S-a salvat");}


    public static Catalog loadContentFromFile(String path) throws InvalidCatalogException {
        ObjectMapper objectMapper = new ObjectMapper();
        Catalog catalog = new Catalog();
        try {
            catalog = objectMapper.readValue(new File(path), Catalog.class);
        } catch (IOException e) {
            System.out.println(e);
        }
        return catalog;
    }

    public void view(String path){
        try
        {

//constructor of file class having file as argument
            File file = new File(path);
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())         //checks file exists or not
                desktop.open(file);              //opens the specified file
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
