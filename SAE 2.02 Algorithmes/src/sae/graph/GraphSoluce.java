package sae.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphSoluce {
    private List<Node> cheminSolution;

    public GraphSoluce() {
        cheminSolution = new ArrayList<>();
    }

    public void add(Node noeud) {
        cheminSolution.add(noeud);
    }

    public List<Node> getSoluce() {
        return cheminSolution;
    }
}