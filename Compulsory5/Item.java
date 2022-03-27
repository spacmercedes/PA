import javax.swing.*;
import java.io.Serializable;


public abstract class Item  implements Serializable {


    protected String id;
    protected String title;
    protected String location;
    protected String year;
    protected String author;



    public Item(String id, String title, String location, String year, String author){
        this.id=id;
        this.title=title;
        this.location=location;
        this.year=year;
        this.author = author;

    }

    public String getId(){
        return id;
    }
}

