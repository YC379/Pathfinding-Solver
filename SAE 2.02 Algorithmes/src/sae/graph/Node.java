package sae.graph;

import java.util.HashSet;
import java.util.Set;
import sae.dungeon.Coord;

public class Node {
    private String nom;
    private Set<Node> voisins;
    private Coord coordonnees;

    public Node(String nom, Coord coordonnees) {
        this.nom = nom;
        this.coordonnees = coordonnees;
        this.voisins = new HashSet<>();
    }

    public Set<Node> neighbors() {
        return voisins; 
    }

    public void addNeigbour(Node noeudVoisin) {
        voisins.add(noeudVoisin);
    }

    public String getName() {
        return nom;
    }

    public Coord getCoord() {
        return coordonnees;
    }

    @Override
    public String toString() {
        return "Noeud " + nom;
    }

    @Override
    public boolean equals(Object objet) {
        if (this == objet) return true;
        if (!(objet instanceof Node)) return false;
        Node autreNoeud = (Node) objet;
        if (nom == null) {
            if (autreNoeud.nom != null) return false;
        } else if (!nom.equals(autreNoeud.nom)) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        return nom != null ? nom.hashCode() : 0;
    }
}