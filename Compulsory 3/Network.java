package com.example.Compulsory;
import java.util.*;

public class Network {
    private List<Node> nodes = new ArrayList<Node>();
    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

public void addNodes(Node node ){
        this.nodes.add(node);
    }



    public void printNodes(){
        System.out.println("network Locations are:");
        for(Node n: nodes)
            System.out.print(n.getName()+"( "+  n.getLocation()+")"+"\n");
    }



    }




