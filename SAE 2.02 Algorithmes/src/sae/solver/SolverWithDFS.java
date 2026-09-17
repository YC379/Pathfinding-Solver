package sae.solver;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import sae.graph.Node;

public class SolverWithDFS extends SolverGeneric {
	
    private Map<Node, Node> predecesseurs; 
    private Set<Node> noeudsMarques;
    private boolean sortieTrouvee;

    // Constructeur avec un noeud de départ et un noeud d'arrivée
    public SolverWithDFS(Node noeudDepart, Node noeudArrivee) {
        super(noeudDepart, noeudArrivee);
    }
    
    // Lance la résolution de l'algo DFS puis la reconstruction du chemin
    @Override
    protected void resolve() {
        predecesseurs = new HashMap<>();
        noeudsMarques = new HashSet<>();
        sortieTrouvee = false;

        parcoursProfondeur(getStartingNode());
        reconstituerChemin(); 
    }
    
    // Explore un chemin jusqu'au bout ou jusqu'à l'arrivée
    private void parcoursProfondeur(Node noeudActuel) {
    	incSteps();
        if (sortieTrouvee) return; 
        
        noeudsMarques.add(noeudActuel); 
        
        if (noeudActuel.equals(getEndingNode())) {
            sortieTrouvee = true;
            return;
        }

        for (Node noeudVoisin : noeudActuel.neighbors()) { 
            incSteps(); 
            if (!noeudsMarques.contains(noeudVoisin)) {
                predecesseurs.put(noeudVoisin, noeudActuel);
                parcoursProfondeur(noeudVoisin); 
            }
        }
    }
    
    // Remonte l'historique des prédécesseurs pour créer le chemin final
    private void reconstituerChemin() {
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
        return "Parcours en Profondeur (DFS)";
    }
}