import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {

    private final List<Tile> tiles;

    public Bag() {

        this.tiles = new ArrayList<Tile>();

        for (char c = 'a'; c < 'z'; c++) {
            Random rand = new Random();
            int points = rand.nextInt (10)+1;
            Tile tile = new Tile(c,points);
            this.tiles.add(tile);
        }
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();

        for (int i = 0; i < howMany; i++) {
            if (this.tiles.isEmpty()) {
                break;
            }else{
                for(Tile t: this.tiles){
                    extracted.add(t);
                }
            }

        }
        return extracted;
    }


}