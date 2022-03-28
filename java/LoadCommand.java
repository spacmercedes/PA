

import java.io.IOException;

public class LoadCommand implements Command{

    private String path;

    public LoadCommand(String path){
        this.path=path;
    }

    @Override
    public void execute(Catalog catalog)  {
        try {
            catalog.loadContentFromFile(path);
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        }
    }
}