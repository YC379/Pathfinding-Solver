# 🗺️ Pathfinding Solver - Donjon Modélisation

## 📝 Présentation
Création d'un solveur en **Java** permettant de trouver le plus court chemin dans un donjon modélisé sous forme de graphes. Ce projet académique a été développé en binôme dans le cadre de ma première année de BUT Informatique (IUT de Laval).

L'objectif principal du projet était d'aborder des concepts algorithmiques avancés (théorie des graphes) et d'évaluer concrètement la performance de différentes approches de résolution.

---

## ✨ Fonctionnalités implémentées

- **Modélisation de Graphes :** Transformation d'un donjon (grille/carte) en une structure de graphes manipulable par le code.
- **Algorithmes de Pathfinding :** Implémentation de plusieurs algorithmes de recherche de chemin :
  - `A*` (A-Star) : Algorithme heuristique optimal.
  - `BFS` (Breadth-First Search - Parcours en largeur) : Recherche du chemin le plus court sans heuristique.
  - `DFS` (Depth-First Search - Parcours en profondeur) : Exploration des chemins jusqu'au bout de chaque branche avant de rebrousser chemin.
- **Tests de performance (Benchmark) :** Conception du script d'évaluation réalisant des calculs 10 000 itérations afin de comparer l'efficacité, la rapidité et la consommation de ressources de chaque approche.

---

## 🛠️ Technologies & Outils

- **Langage utilisé :** Java
- **Concepts clés :** Structures de données, Théorie des graphes, Heuristiques, Benchmarking

---

## 📄 Rapport d'étude et d'analyse

Dans le cadre de ce projet académique, nous avons rédigé un rapport complet détaillant l'architecture logicielle, la complexité algorithmique, et l'analyse approfondie des résultats de notre benchmark (comparaison des performances entre A*, BFS et DFS).

👉 **[Consulter le rapport final (PDF)](./rapport-pathfinding.pdf)**

---

## 🚀 Comment lancer le projet

**Prérequis :** Avoir Java (version 17 minimum) installé sur votre machine.

1. Clonez ce dépôt : `git clone https://github.com/YC379/Pathfinding-Solver.git`
2. Ouvrez le dossier dans votre IDE (IntelliJ, Eclipse, etc.).
3. Exécutez le fichier `Scenarios.java` situé dans le dossier `src/sae/` pour lancer le programme.
