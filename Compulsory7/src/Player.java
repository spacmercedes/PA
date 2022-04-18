import java.util.List;

import static java.lang.Thread.sleep;

public class Player implements Runnable {
    private String name;
    private Game game;
    private boolean running;


    public void setGame(Game game) {
        this.game = game;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private boolean submitWord() {

        List<Tile> extracted = game.getBag().extractTiles(7);
        String word = "";
        StringBuilder sb = new StringBuilder();

        if (extracted.isEmpty()) {
            return false;
        } else {
            for (Tile t : extracted) {
                word = word + t;
            }

            game.getBoard().addWord(this, word);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return true;
        }
    }

    @Override
    public void run() {
        while(true){
            if(!this.submitWord()){
                return;
            }
        }


    }


}