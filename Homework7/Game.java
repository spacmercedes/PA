import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.concurrent.Executors;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final MockDictionary dictionary= new MockDictionary();
    private List<Player> players = new ArrayList<>();

    public Game(){
    }

    public Game(List<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){ //adauga un nou jucator si porneste jocul
        players.add(player);
        player.setGame(this);
    }

    public void play(){
        for(Player player : players){
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    player.run();
                }
            });
        }
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    static void compulsory(){

        Game game = new Game();
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }

    public static void main(String[] args) {
        compulsory();
    }
}
