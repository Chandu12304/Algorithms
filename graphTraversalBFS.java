package daaManual;
import java.util.*;

public class graphTraversalBFS {

    // Function to perform BFS starting from node 'i'
    private void bfs(int i, List<Integer>[] adj, List<Integer> vis, Queue<Integer> q, List<Integer> ans) {
        if (q.isEmpty()) {
            q.add(i);
            vis.set(i, 1); // Mark the starting node as visited
        }
        while (!q.isEmpty()) {
            int el = q.peek(); // Get the front element in the queue
            for (int x : adj[el]) {
                if (vis.get(x) == 0) { // If the node has not been visited
                    q.add(x); // Add to queue
                    vis.set(x, 1); // Mark as visited
                }
            }
            q.remove(); // Pop the front element from the queue
            ans.add(el); // Add the element to the BFS result
        }
    }

    // Function to return Breadth First Traversal of the given graph
    public List<Integer> bfsOfGraph(int V, List<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS
        List<Integer> ans = new ArrayList<>(); // To store BFS result
        List<Integer> vis = new ArrayList<>(Collections.nCopies(V, 0)); // Visited list initialized to 0

        // Call BFS starting from node 0
        bfs(0, adj, vis, q, ans);

        return ans; // Return the BFS traversal result
    }

    public static void main(String[] args) {
        // Example usage:
        int V = 5; // Number of vertices
        List<Integer>[] adj = new ArrayList[V]; // Adjacency list for the graph

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Adding edges
        adj[0].add(1);
        adj[0].add(2);
        adj[1].add(3);
        adj[2].add(4);

        graphTraversalBFS graphTraversal = new graphTraversalBFS();
        List<Integer> bfsResult = graphTraversal.bfsOfGraph(V, adj);

        // Print BFS result
        System.out.println("BFS Traversal of the graph: " + bfsResult);
    }
}