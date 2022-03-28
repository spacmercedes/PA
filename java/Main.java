
import javax.xml.catalog.CatalogFeatures;
import java.io.IOException;
import java.nio.file.Paths;
public class Main {
    public static void main(String args[]) {

        String pathC = "C:\\Users\\spacm\\IdeaProjects\\Laborator5Homework\\src\\main\\java\\catalog.txt";
        String pathFile = "C:\\Users\\spacm\\IdeaProjects\\Laborator5Homework\\src\\main\\java\\cursuri.txt";
        Catalog catalog = new Catalog(pathC,"Bibliografie");


        String pathB1 = "src/main/java/cursuri.txt";
        String pathA = "src/main/java/Cei 3 purcelusi.pdf";

        Book b1 = new Book("book2", "Cei 3 purcelusi", pathB1, "1988", "Fratii Grimm");
        Article a= new Article("book3", "Cursuri", pathA, "1984", "Spac");
//        catalog.add(b1);
//        catalog.add(a);

        System.out.println("books  " + b1.getId() +" "+ a.getId());

        try {
            Command comanda4 = new AddCommand(a);
                comanda4.execute(catalog);
            Command comandab = new AddCommand(b1);
            comandab.execute(catalog);
////            Command comanda1 = new InfoCommand();
////                comanda1.execute(catalog);
//            Command comanda2 = new SaveCommand(pathC);
//                comanda2.execute(catalog);
//            Command comanda3 = new ReportCommand();
//                comanda3.execute(catalog);

//
//
            Command comanda5 = new ListCommand();
                comanda5.execute(catalog);
//            Command comanda6 = new LoadCommand("catalog.txt");
//                comanda6.execute(catalog);
//           Command comanda7 = new ViewCommand(pathFile);
//            comanda7.execute(catalog);




        } catch (Exception e) {
            System.err.println("Error la info" + e);
        }


    }}
