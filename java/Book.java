import java.awt.event.ActionEvent;

public class Book extends Item {
    private String type="Book";

    public Book(String id, String title, String location, String year, String author) {
        super(id,title,location,year,author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", year='" + year + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

