import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        var intersections = IntStream.rangeClosed(0,9)
                .mapToObj(i -> new Intersection("v" + i))
                .toArray(Intersection[]::new);

        List<Intersection> intersectionsList = new ArrayList<>();
        intersectionsList.addAll( Arrays.asList(intersections) );

        System.out.println(intersectionsList);

        List<Intersection> sortedList = intersectionsList.stream()
                .sorted(Comparator.comparing(Intersection::getName))
                .collect(Collectors.toList());

        System.out.println(sortedList);

        List<Streets> streetsList = new LinkedList<>();

        streetsList.add(new Streets("s1",2));
        streetsList.add(new Streets("s2",2));
        streetsList.add(new Streets("s3",2));
        streetsList.add(new Streets("s4",3));
        streetsList.add(new Streets("s5",2));
        streetsList.add(new Streets("s6",2));
        streetsList.add(new Streets("s7",3));
        streetsList.add(new Streets("s8",1));
        streetsList.add(new Streets("s9",2));
        streetsList.add(new Streets("s10",1));
        streetsList.add(new Streets("s11",1));
        streetsList.add(new Streets("s12",1));
        streetsList.add(new Streets("s13",3));
        streetsList.add(new Streets("s14",1));
        streetsList.add(new Streets("s15",2));

        List<Streets> sortedStreetsList = streetsList.stream()
                .sorted(Comparator.comparingInt(Streets::getLength)).toList();
        sortedStreetsList.forEach(streets -> System.out.println(streets.getName()));


        Set<Intersection> intersect = new HashSet< Intersection >(Arrays.stream(intersections).toList());




    }
}