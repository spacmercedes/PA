import java.util.HashMap;
import java.util.Map;

public class Tile {
    private final char letter;
    private final int points;

    static Map<Character, Tile> tile; //pentru fiecare caracter, de cate ori se repeta in bag

    public Tile(char letter, int points) {
        tile = new HashMap<>();
        this.letter = letter;
        this.points = points;
        tile.put(letter, this); // o sa puna litera in tile
    }

    public static Map<Character, Tile> getTile() {
        return tile;
    }

    public static void setTile(Map<Character, Tile> tile) {
        Tile.tile = tile;
    }

    public char getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "letter=" + letter +
                ", points=" + points +
                '}';
    }
}
