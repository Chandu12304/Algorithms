package daaManual;

import java.util.*;

public class TopologicalSort {
    
    // Helper function to perform DFS and populate the stack in topological order
    private void dfs(int i, ArrayList<Integer>[] adj, ArrayList<Integer> vis, Stack<Integer> st) {
        vis.set(i, 1); // Mark the current node as visited
        for (int x : adj[i]) { // Traverse all adjacent nodes
            if (vis.get(x) != 1) {
                dfs(x, adj, vis, st); // Recursive DFS call
            }
        }
        st.push(i); // Push the current node onto the stack
    }

    // Function to return a list containing vertices in Topological order
    public ArrayList<Integer> topoSort(int V, ArrayList<Integer>[] adj) {
        ArrayList<Integer> ans = new ArrayList<>(); // Store the topological order
        ArrayList<Integer> vis = new ArrayList<>(Collections.nCopies(V, 0)); // Visited array
        Stack<Integer> st = new Stack<>(); // Stack to store the order
        
        // Perform DFS for each unvisited node
        for (int i = 0; i < V; i++) {
            if (vis.get(i) != 1) {
                dfs(i, adj, vis, st); // Start DFS for each unvisited node
            }
        }

        // Pop elements from the stack to get the topological order
        while (!st.isEmpty()) {
            int el = st.pop(); // Get the top element
            ans.add(el); // Add it to the result list
        }

        return ans; // Return the topological order
    }

    public static void main(String[] args) {
        TopologicalSort graph = new TopologicalSort();
        
        // Number of vertices
        int V = 6;
        
        // Adjacency list representation of the graph
        ArrayList<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Example graph
        adj[5].add(2);
        adj[5].add(0);
        adj[4].add(0);
        adj[4].add(1);
        adj[2].add(3);
        adj[3].add(1);
        
        // Perform topological sort
        ArrayList<Integer> result = graph.topoSort(V, adj);
        
        // Print topological order
        System.out.println("Topological Sort: " + result);
    }
}

