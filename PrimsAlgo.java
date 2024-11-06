package daaManual;

import java.util.*;

public class PrimsAlgo {

    // Function to find the sum of weights of edges of the Minimum Spanning Tree
    public int primsAlgo(int V, ArrayList<int[]> adj[]) {
        // Priority Queue to pick the minimum weight edge, with custom comparator
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Visited array to track the nodes that are already included in the MST
        boolean[] vis = new boolean[V];

        // Variable to store the sum of weights in the MST
        int sum = 0;

        // Start with node 0 and weight 0
        pq.add(new int[]{0, 0}); // {weight, node}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // Get the node with the minimum weight
            int node = curr[1];
            int wt = curr[0];

            // If node is already visited, skip
            if (vis[node]) continue;

            // Mark the node as visited
            vis[node] = true;

            // Add the edge's weight to the total sum
            sum += wt;

            // Iterate through the adjacent nodes of the current node
            for (int[] neighbor : adj[node]) {
                int adjNode = neighbor[0];
                int edgeWeight = neighbor[1];

                // If adjacent node is not visited, add it to the priority queue
                if (!vis[adjNode]) {
                    pq.add(new int[]{edgeWeight, adjNode});
                }
            }
        }

        return sum; // Return the sum of weights of edges in the MST
    }

    public static void main(String[] args) {
        // Example usage
        int V = 5; // Number of vertices

        // Adjacency list to store the graph using ArrayList[] format
        ArrayList<int[]> adj[] = new ArrayList[V];

        // Initializing adjacency list
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Adding edges to the graph (Example: {adjacent node, edge weight})
        adj[0].add(new int[]{1, 2});
        adj[1].add(new int[]{0, 2});
        adj[0].add(new int[]{3, 6});
        adj[3].add(new int[]{0, 6});
        adj[1].add(new int[]{2, 3});
        adj[2].add(new int[]{1, 3});
        adj[1].add(new int[]{3, 8});
        adj[3].add(new int[]{1, 8});
        adj[1].add(new int[]{4, 5});
        adj[4].add(new int[]{1, 5});
        adj[2].add(new int[]{4, 7});
        adj[4].add(new int[]{2, 7});

        // Running Prim's Algorithm
        PrimsAlgo pa = new PrimsAlgo();
        int result = pa.primsAlgo(V, adj);

        // Output the result
        System.out.println("Sum of weights of edges of the Minimum Spanning Tree: " + result);
    }
}
