package daaManual;

import java.util.*;

public class TransitiveClosure {

    // Helper method to update the graph based on the left and right arrays
    private void updateGraph(int[][] graph, List<Integer> la, List<Integer> ra) {
        for (int i = 0; i < la.size(); i++) {
            for (int j = 0; j < ra.size(); j++) {
                graph[la.get(i)][ra.get(j)] = 1;
            }
        }
    }

    // Method to compute the transitive closure of the graph
    public int[][] transitiveClosure(int N, int[][] graph) {
        int k = 0;

        // Edge case: setting self-loops
        for (int i = 0; i < N; i++) {
            graph[i][i] = 1;
        }

        // Applying transitive closure
        while (k < N) {
            List<Integer> la = new ArrayList<>();
            List<Integer> ra = new ArrayList<>();

            // Collect nodes with a path to node k (left array)
            for (int i = 0; i < N; i++) {
                if (graph[i][k] == 1) {
                    la.add(i);
                }
            }

            // Collect nodes reachable from node k (right array)
            for (int j = 0; j < N; j++) {
                if (graph[k][j] == 1) {
                    ra.add(j);
                }
            }

            // Update the graph based on la and ra
            updateGraph(graph, la, ra);

            k += 1;
        }
        return graph;
    }

    // Main function for testing the transitive closure logic
    public static void main(String[] args) {
        TransitiveClosure tc = new TransitiveClosure();

        int N = 4; // Number of vertices

        // Initial graph (adjacency matrix)
        int[][] graph = {
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
            {0, 0, 0, 0}
        };

        // Compute the transitive closure
        int[][] result = tc.transitiveClosure(N, graph);

        // Print the resulting transitive closure
        System.out.println("Transitive Closure:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
