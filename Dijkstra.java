package daaManual;

import java.util.*;

public class Dijkstra {

    // Function to find the shortest distance of all the vertices from the source vertex S
    public int[] dijkstra(int V, List<List<int[]>> adj, int S) {
        // Priority Queue to store (distance, node) pairs
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Distance array initialized to a large value
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9); // Fill with a large number
        dist[S] = 0; // Distance to source is 0
        
        // Add the source node to the priority queue
        pq.add(new int[]{0, S}); // {distance, node}

        while (!pq.isEmpty()) {
            int dis = pq.peek()[0]; // Get the minimum distance
            int node = pq.peek()[1]; // Get the associated node
            pq.poll(); // Remove the minimum distance node

            // Iterate through all adjacent nodes of the current node
            for (int[] it : adj.get(node)) {
                int adjNode = it[0]; // Adjacent node
                int edgeWeight = it[1]; // Weight of the edge

                // If a shorter path is found
                if (dis + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = dis + edgeWeight; // Update the shortest distance
                    pq.add(new int[]{dist[adjNode], adjNode}); // Add to the priority queue
                }
            }
        }
        
        return dist; // Return the distance array
    }

    public static void main(String[] args) {
        // Example usage
        int V = 5; // Number of vertices

        // Adjacency list to store the graph
        List<List<int[]>> adj = new ArrayList<>();

        // Initializing adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges to the graph (Example: {adjacent node, edge weight})
        adj.get(0).add(new int[]{1, 10});
        adj.get(0).add(new int[]{4, 5});
        adj.get(1).add(new int[]{2, 1});
        adj.get(2).add(new int[]{3, 4});
        adj.get(4).add(new int[]{1, 3});
        adj.get(4).add(new int[]{2, 9});
        adj.get(4).add(new int[]{3, 2});
        
        // Running Dijkstra's Algorithm
        Dijkstra dij = new Dijkstra();
        int S = 0; // Source vertex
        int[] result = dij.dijkstra(V, adj, S);

        // Output the result
        System.out.println("Shortest distances from source vertex " + S + ":");
        for (int i = 0; i < result.length; i++) {
            System.out.println("Vertex " + i + ": " + result[i]);
        }
    }
}

