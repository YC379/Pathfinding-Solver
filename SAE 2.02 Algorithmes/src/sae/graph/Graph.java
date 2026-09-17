package sae.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> listeNoeuds;

    public Graph() {
        listeNoeuds = new ArrayList<>();
    }

    public void addNode(Node noeud) {
        listeNoeuds.add(noeud);
    }

    public void addEdge(Node noeud1, Node noeud2) {
        noeud1.addNeigbour(noeud2);
        noeud2.addNeigbour(noeud1);
    }

    @Override
    public String toString() {
        return "Graphe contenant " + listeNoeuds.size() + " noeuds.";
    }
}