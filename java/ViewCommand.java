

public class ViewCommand implements Command{

  public String path;

    public ViewCommand(String path ) {
this.path=path;
    }


    @Override
    public void execute(Catalog catalog) {
        catalog.view(this.path);

    }
}
