
import javax.xml.catalog.CatalogFeatures;
import java.io.IOException;
import java.nio.file.Paths;
public class Main {
    public static void main(String args[]) {

        Main app = new Main();

      app.testCreateSave();
      app.testLoadView();

    }



    private void testCreateSave() {
        Catalog catalog = new Catalog("Bibliografie");
        var book = new Book("article1", "Capra cu trei iezi", "src/main/java/Capra cu trei iezi.pdf", "1999","pp" );
        var article = new Article("book1", "Cei 3 purcelusi", "src/main/java/Cei 3 purcelusi.pdf", "2000", "pp");
        catalog.add(book);
        catalog.add(article);

        try {
            CatalogUtil.save(catalog, "catalog.txt");
        } catch (IOException exception) {
            System.out.println(exception);
        }}

        private void testLoadView() {
            try {
                Catalog catalog = CatalogUtil.load("Catalog.txt");
            } catch (InvalidCatalogException exception) {
                System.out.println(exception);
            }
        }


    }
//        String pathC = "catalog.txt";
//        //System.out.println(Paths.get(pathC));
//        // Catalog c = new Catalog(Paths.get(pathC));
//        Catalog catalog = new Catalog(pathC);
//
//        String pathB = "Capra cu trei iezi.pdf";
//        Book b = new Book("id1","Capra cu trei iezi", "pathB", "1875","Ion Creanga");
//
//
//        catalog.add(b);
//
//        System.out.println("Book has the id " + b.getId());
//        CatalogUtil.save(catalog,pathB);
//        Catalog catalogUtil= CatalogUtil.load(pathB);









//public class Main {
//    public static void main(String[] args) {
//
//        Catalog catalog = new Catalog();

//        catalog.add("Book","id1", "Capra cu trei iezi", "Capra cu trei iezi.pdf","1875","Ion Creanga");
//        catalog.add("Book","id2", "Cei 3 purcelusi", "Cei 3 purcelusi.pdf","1900","James Halliwell-Phillipps");
//        catalog.toString();
//        catalog.save();
//        //catalog.load();
//    }
//}
//
