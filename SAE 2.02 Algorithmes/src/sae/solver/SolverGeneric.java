package sae.solver;

import sae.graph.GraphSoluce;
import sae.graph.Node;

public abstract class SolverGeneric implements Solver {

    private Node noeudDepart;
    private Node noeudArrive;
    private int compteurEtapes;
    private GraphSoluce solutionGraphe;

    public SolverGeneric(Node noeudDepart, Node noeudArrive) {
        this.noeudDepart = noeudDepart;
        this.noeudArrive = noeudArrive;
        this.compteurEtapes = 0;
        this.solutionGraphe = new GraphSoluce();
    }

    public Node getStartingNode() { 
    	return noeudDepart;
    }
    
    public Node getEndingNode() { 
    	return noeudArrive;
    }
    
    @Override
    public int getSteps() { 
    	return compteurEtapes;
    }
    
    @Override
    public GraphSoluce getGraphSoluce() { 
    	return solutionGraphe;
    }

    public void incSteps() { 
    	this.compteurEtapes++;
    }

    @Override
    public void solve() {
        initialiserResolution();
        resolve();
    }

    protected abstract void resolve();

    private void initialiserResolution() {
        this.compteurEtapes = 0;
        this.solutionGraphe = new GraphSoluce();
    }
}