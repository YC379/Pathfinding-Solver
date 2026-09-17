package sae.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sae.dungeon.Direction;
import sae.dungeon.Dungeon;
import sae.dungeon.DungeonSoluce;
import sae.dungeon.Room;
import sae.graph.Graph;
import sae.graph.GraphSoluce;
import sae.graph.Node;

public class Dungeon2Graph {

    private Graph graphe;
    private Map<Room, Node> associationSalleVersNoeud;
    private Map<Node, Room> associationNoeudVersSalle;
    
    // Fait le graphe et crée les arêtes avec les salles du donjon
    public Dungeon2Graph(Dungeon donjon) {
        this.graphe = new Graph();
        this.associationSalleVersNoeud = new HashMap<>();
        this.associationNoeudVersSalle = new HashMap<>();

        for (Room salle : donjon.getRooms()) {
            Node noeud = new Node(salle.getName(), salle.getCoords());
            graphe.addNode(noeud);
            associationSalleVersNoeud.put(salle, noeud);
            associationNoeudVersSalle.put(noeud, salle);
        }

        for (Room salle : donjon.getRooms()) {
            Node noeudCourant = associationSalleVersNoeud.get(salle);
            for (Room salleVoisine : salle.getNextRooms().values()) {
                Node noeudVoisin = associationSalleVersNoeud.get(salleVoisine);
                noeudCourant.addNeigbour(noeudVoisin);
            }
        }
    }
    
    // Renvoie le noeud correspondant a une salle
    public Node mappedNode(Room salle) {
        return associationSalleVersNoeud.get(salle);
    }
    
    // Transforme le chemin de noeuds en liste de directions
    public DungeonSoluce transform(GraphSoluce solutionGraphe) {
        DungeonSoluce solutionDonjon = new DungeonSoluce();
        List<Node> noeudsTraverses = solutionGraphe.getSoluce();

        if (noeudsTraverses == null || noeudsTraverses.isEmpty()) return solutionDonjon;

        for (int i = 0; i < noeudsTraverses.size() - 1; i++) {
            Room salleCourante = associationNoeudVersSalle.get(noeudsTraverses.get(i));
            Room salleSuivante = associationNoeudVersSalle.get(noeudsTraverses.get(i + 1));

            for (Map.Entry<Direction, Room> liaison : salleCourante.getNextRooms().entrySet()) {
                if (liaison.getValue().equals(salleSuivante)) {
                    solutionDonjon.addDirection(liaison.getKey());
                    break;
                }
            }
        }
        return solutionDonjon;
    }
}