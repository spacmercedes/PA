import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class Game extends ThreadDeath{

    private final Bag bag = new Bag();

    private final Board board = new Board();
//    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }
    public void play() {
        for (Player p : players) {
           new Thread(p).start();
        }
    }

}