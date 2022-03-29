import java.util.*;

public class Streets {

   private String name;
    private int length;
    private Intersection source;
    private Intersection destination;

    public void setSource(Intersection source) {
        this.source = source;
    }


    public Intersection getDestination() {
        return destination;
    }

    public Intersection getSource() {
        return this.source;
    }



    public void setDestination(Intersection destination) {
        this.destination = destination;
    }
    public Streets(String name, int length) {
        this.name = name;
        this.length = length;
        this.setDestination(destination);
        this.setSource(source);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int neighbours(List<Streets> streets) {
        /*return (int) streets.stream()
                .filter((s ->s.getSource() = this.source || s.getDestination() = this.destination)
                        && s.getName != this.name)
                .count();
                */
        int k=0;
        for(Streets s: streets){
            if((s.getSource() == this.source) || (s.getDestination() == this.destination));
k++;
    }
        return k;
    }



    public void displayStreets(ArrayList<Streets> streets){
        int minLength = 1;

        streets.stream().filter(s -> s.getLength()>minLength)
                .filter(s-> s.neighbours(streets)>=3)
                .forEach(s-> System.out.println(s));
    }



}