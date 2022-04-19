import java.util.List;

public class Player {
    private String name;
    private Game game;
    private boolean running;
    int nrOfTiles = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setGame(Game game) {
        this.game = game;
    }

    public boolean submitWord(){
        List<Tile> extracted = game.getBag().extractTiles(7 - nrOfTiles);
        if(extracted.isEmpty()){
            return false;
        }
        StringBuilder word = new StringBuilder();

        // create a word with all the extracted tiles

        int randNr = RandomGenerator.getRandomNumber(3,7);
        int counter = 0;
        for (Tile tile : extracted) {
            counter++;
            word.append(tile.getLetter());
            if(counter == randNr) break;
        }

        if(MockDictionary.isWord(word.toString())){
            game.getBoard().addWord(this, word.toString());
            System.out.println("player: " + getName() + " SUBMITTED A WORD: " + word.toString());

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public void run(){
        running = true;
        int pointsAccumulated = 0;
        boolean flag = submitWord();
        while(flag){
            flag = submitWord();
        }
        running = false;
    }

}
