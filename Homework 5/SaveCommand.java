

import java.io.IOException;

public class SaveCommand implements Command {
    private String path;

    public SaveCommand(String path) {
        this.path = path;
    }

    @Override
    public void execute(Catalog catalog) {

        try {
            catalog.saveToFile(catalog,this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}