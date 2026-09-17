package sae.solver;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import sae.graph.Node;

public class SolverWithBFS extends SolverGeneric {
	
	// Constructeur avec un noeud de départ et un noeud d'arrivée
    public SolverWithBFS(Node noeudDepart, Node noeudArrivee) {
        super(noeudDepart, noeudArrivee);
    }
    
    // Utilise une file d'attente pour explorer le graphe en entier
    @Override
    protected void resolve() {
        Queue<Node> fileAttente = new LinkedList<>();
        Set<Node> noeudsMarques = new HashSet<>();
        Map<Node, Node> predecesseurs = new HashMap<>();

        Node depart = getStartingNode();
        noeudsMarques.add(depart);
        fileAttente.add(depart);

        while (!fileAttente.isEmpty()) { 
            incSteps();
            Node noeudCourant = fileAttente.poll();

            if (noeudCourant.equals(getEndingNode())) {
                break;
            }

            for (Node noeudVoisin : noeudCourant.neighbors()) { 
                incSteps();
                if (!noeudsMarques.contains(noeudVoisin)) { 
                    predecesseurs.put(noeudVoisin, noeudCourant);
                    noeudsMarques.add(noeudVoisin); 
                    fileAttente.add(noeudVoisin); 
                }
            }
        }
        reconstituerChemin(predecesseurs);
    }
    
    // Remonte l'historique des prédécesseurs pour créer le chemin final
    private void reconstituerChemin(Map<Node, Node> predecesseurs) {
        Node noeudCourant = getEndingNode();
        List<Node> cheminInverse = new ArrayList<>();
        
        while (noeudCourant != null && predecesseurs.containsKey(noeudCourant)) {
            cheminInverse.add(noeudCourant);
            noeudCourant = predecesseurs.get(noeudCourant);
        }
        cheminInverse.add(getStartingNode());
        
        Collections.reverse(cheminInverse);
        for (Node noeud : cheminInverse) {
            getGraphSoluce().add(noeud);
        }
    }
    
    // Nom de l'algo pour les logs de la console
    @Override
    public String toString() {
        return "Parcours en Largeur (BFS)";
    }
}