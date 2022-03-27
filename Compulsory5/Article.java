public class Article extends Item{

    private String type = "Article";

public Article(String id, String title, String location, String year, String author){
    super(id,title,location,year,author);
}

    @Override
    public String toString() {return super.toString();}
}
