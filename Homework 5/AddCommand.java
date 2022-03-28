
public class AddCommand implements Command {

    private Item item;

    public AddCommand(Item item){
        this.item=item;

    }

    @Override
    public void execute(Catalog catalog) {
        catalog.add(item);
    }
}

