import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<String> words;

    public Board() {
        this.words = new ArrayList<String>();
    }

    public void addWord(Player player, String word) {
        this.words.add(word);
        System.out.println(player.getName() + ": " + word);
    }
    @Override
    public String toString() {
        return this.words.toString();
    }
}