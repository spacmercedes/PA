import java.util.*;

public class Bag {
    private Map<Tile, Integer> bagOfTiles;
    private int numberOfTiles = 0;
    private static final int TILES_PER_LETTER = 10;

    public Bag() {
        bagOfTiles = new HashMap<>();
        for (char c = 'a'; c < 'z'; c++) {
            bagOfTiles.put(new Tile(c, RandomGenerator.getRandomNumber(1,11)), TILES_PER_LETTER);
            numberOfTiles += TILES_PER_LETTER;
        }
    }

    public Bag(Map<Tile, Integer> bagOfTiles){
        this.bagOfTiles = bagOfTiles;
        numberOfTiles = bagOfTiles.size();
    }

    public Map<Tile, Integer> getBagOfTiles() {
        return bagOfTiles;
    }

    public void setBagOfTiles(Map<Tile, Integer> bagOfTiles) {
        this.bagOfTiles = bagOfTiles;
    }

    public int getNumberOfTiles() {
        return numberOfTiles;
    }

    public void setNumberOfTiles(int numberOfTiles) {
        this.numberOfTiles = numberOfTiles;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Tile checkForCharacterInBag(char character) { // verific daca caaracterul exista
        if (numberOfTiles == 0) {
            return null;
        }
        for (Map.Entry<Tile, Integer> entry : bagOfTiles.entrySet()) {
            Tile tile = entry.getKey();
            if (tile.getLetter() == character) {
                return bagOfTiles.get(tile) != 0 ? tile : null;
            }
        }
        return null;
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        if (bagOfTiles.isEmpty()) {
            return extracted;
        }
        if (numberOfTiles <= howMany) {
            howMany = numberOfTiles;
        }
        for (int i = 0; i < howMany; i++) {
            while (numberOfTiles != 0) {
                // generate a random character from the english alphabet
                char random = (char) getRandomNumber(0, 26);
                char str = Character.toString(97 + random).charAt(0);
                // check that the character is in the bag
                Tile tileTemp = checkForCharacterInBag(str);
                if(tileTemp != null){
                    extracted.add(tileTemp);
                    bagOfTiles.replace(tileTemp, bagOfTiles.get(tileTemp) - 1);
                    numberOfTiles--;
                    break;
                }
            }
        }
        System.out.println("Bag, extracted tiles:" + extracted);
        return extracted;
    }
}
