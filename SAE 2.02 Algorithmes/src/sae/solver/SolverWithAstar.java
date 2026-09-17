package sae.solver;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import sae.graph.Node;

public class SolverWithAstar extends SolverGeneric {
	
	// Constructeur avec un noeud de départ et un noeud d'arrivée
    public SolverWithAstar(Node noeuddebut, Node noeudArrive) {
        super(noeuddebut, noeudArrive);
    }
    
    // Utilise une file prioritaire pour diriger l'exploration vers l'arrivée
    @Override
    protected void resolve() {
        Set<Node> listeFerme = new HashSet<>(); 
        Map<Node, Double> couts = new HashMap<>();
        Map<Node, Node> pi = new HashMap<>();

        Node debut = getStartingNode();
        Node fin = getEndingNode();

        Comparator<Node> comparateur = new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                double f1 = couts.getOrDefault(n1, 0.0) + distance(n1, fin);
                double f2 = couts.getOrDefault(n2, 0.0) + distance(n2, fin);
                return Double.compare(f1, f2);
            }
        };

        PriorityQueue<Node> listeOuverte = new PriorityQueue<>(11, comparateur);

        couts.put(debut, 0.0);
        listeOuverte.add(debut);

        while (!listeOuverte.isEmpty()) {
            incSteps();
            Node noeudActuel = listeOuverte.poll(); 

            if (noeudActuel.equals(fin)) {
                reconstituerChemin(pi); 
                return; 
            }

            for (Node noeudVoisin : noeudActuel.neighbors()) { 
                incSteps();
                double nouveauCout = couts.get(noeudActuel) + 1.0;

                boolean danslisteFerme = listeFerme.contains(noeudVoisin);
                boolean dansListeOuverte = listeOuverte.contains(noeudVoisin);

                if (!(danslisteFerme || (dansListeOuverte && nouveauCout >= couts.getOrDefault(noeudVoisin, Double.MAX_VALUE)))) {
                    pi.put(noeudVoisin, noeudActuel);
                    couts.put(noeudVoisin, nouveauCout);
                    
                    if (!dansListeOuverte) {
                        listeOuverte.add(noeudVoisin); 
                    } else {
                        listeOuverte.remove(noeudVoisin);
                        listeOuverte.add(noeudVoisin);
                    }
                }
            }
            listeFerme.add(noeudActuel);
        }
    }
    
    // Calcule la distance de Manhattan entre deux noeuds (l'heuristique)
    private double distance(Node n1, Node n2) {
        return Math.abs(n1.getCoord().getX() - n2.getCoord().getX()) + Math.abs(n1.getCoord().getY() - n2.getCoord().getY());
    }
    
    // Remonte l'historique des prédécesseurs pour créer le chemin final
    private void reconstituerChemin(Map<Node, Node> pi) {
        Node noeudActuel = getEndingNode();
        List<Node> cheminInverse = new ArrayList<>();
        
        while (noeudActuel != null && pi.containsKey(noeudActuel)) {
            cheminInverse.add(noeudActuel);
            noeudActuel = pi.get(noeudActuel);
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
        return "Algorithme A*";
    }
}