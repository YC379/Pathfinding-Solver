package sae.solver;

import sae.graph.GraphSoluce;

public interface Solver {
    void solve();
    GraphSoluce getGraphSoluce();
    int getSteps();
}