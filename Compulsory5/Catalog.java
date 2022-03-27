
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.*;

import java.util.ArrayList;
import java.util.List;


public class Catalog implements Serializable {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("List")
    private List<Item> list = new ArrayList<>();


    public Catalog(String name) {
        this.name = name;
    }

    public Catalog() {
    }

    public void add(Item i) {
        list.add(i);

    }


    public Item findById(String id) {

        return (Item) list.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);

    }


    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}