import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private final HashMap<Player, ArrayList<String>> listOfWords;

    public Board(){
        this.listOfWords = new HashMap<>();
    }

    public Board(HashMap<Player, ArrayList<String>> listOfWords) {
        this.listOfWords = listOfWords;
    }

    public void addWord(Player player, String word){ //adauga cuv pr board
        if(listOfWords.containsKey(player)){
            listOfWords.get(player).add(word);
        }
        else
        {
            ArrayList<String> temp = new ArrayList<>(); //adaug cuvintele formate
            temp.add(word);
            listOfWords.put(player, new ArrayList<>());
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "listOfWords=" + listOfWords +
                '}';
    }
}
