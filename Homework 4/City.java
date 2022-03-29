import java.util.*;
import com.github.javafaker.Faker;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.util.SupplierUtil;


public class City {

    private ArrayList<Intersection> intersections;
    private ArrayList<Streets> streets;
    private Map<Intersection, List<Streets>> cityMap;


    public City(ArrayList<Intersection> intersections, ArrayList<Streets> streets, Map<Intersection, List<Streets>> cityMap) {
        this.setIntersection(intersections);
        this.setStreets(streets);
        this.setCityMap(cityMap);
    }

    public ArrayList<Intersection> getIntersection() {

        return this.intersections;
    }

    public ArrayList<Streets> getStreets() {

        return this.streets;
    }

    public void setIntersection(ArrayList<Intersection> intersections) {
        this.intersections = new ArrayList<Intersection>(intersections);
    }

    public void setStreets(ArrayList<Streets> streets) {

        this.streets = new ArrayList<Streets>(streets);
    }

    public Map<Intersection, List<Streets>> getCityMap() {
        return this.cityMap;
    }


    public void setCityMap(Map<Intersection, List<Streets>> cityMap) {
        this.cityMap =new HashMap<Intersection, List<Streets>>();
    }


    public void printCity(){
       for(Intersection i: cityMap.keySet())
          System.out.println(i.getName()+"streets :" + Arrays.asList(getStreets())); //printeaza pentru fiecare inter ce strazi are


    }

    public void generateFakeNames(ArrayList<Intersection> intersections, ArrayList<Streets> streets){
        Faker faker = new Faker();

        this.intersections.forEach(i ->  i.setName(faker.name().fullName()));
        this.streets.forEach(i ->  i.setName(faker.name().fullName()));


    }
    public void graph(){
        Graph<String, DefaultWeightedEdge> graph = GraphTypeBuilder
                .undirected()
                .weighted(true)
                .allowingMultipleEdges(true)
                .allowingSelfLoops(false)
                .vertexSupplier(SupplierUtil.createStringSupplier())
                .edgeSupplier(SupplierUtil.createDefaultWeightedEdgeSupplier())
                .buildGraph();

        intersections.stream().map(Intersection::getName).forEach(graph::addVertex);

streets.forEach(s-> graph.addEdge(s.getSource().getName(), s.getDestination().getName()));

//        graph.setEdgeWeight( 100d);
//        graph.setEdgeWeight( 5d);
//        graph.setEdgeWeight( 1d);

        System.out.println("Prim:");
        for(DefaultWeightedEdge e: new PrimMinimumSpanningTree<>(graph).getSpanningTree()) {
            System.out.println(e);
        }

    }

}
