import java.util.*;

class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

class Solution {
    static int spanningTree(int V, ArrayList<int[]> adj[]) {
        // Sort each adjacency list by weight (adjacentNode[1])
        for (int i = 0; i < V; i++) {
            adj[i].sort((a, b) -> Integer.compare(a[1], b[1]));
        }

        DisjointSet ds = new DisjointSet(V);
        int mstWt = 0;

        // Loop through each node and add edges in sorted order
        for (int i = 0; i < V; i++) {
            for (int[] neighbor : adj[i]) {
                int u = i;
                int v = neighbor[0];
                int wt = neighbor[1];

                if (ds.findUPar(u) != ds.findUPar(v)) {
                    mstWt += wt;
                    ds.unionBySize(u, v);
                }
            }
        }

        return mstWt;
    }
}


class Main {
    public static void main(String[] args) {
        int V = 5;
        ArrayList<int[]> adj[] = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Directly adding edges to the adjacency list
        adj[0].add(new int[]{1, 2});
        adj[0].add(new int[]{2, 1});
        
        adj[1].add(new int[]{0, 2});
        adj[1].add(new int[]{2, 1});

        adj[2].add(new int[]{0, 1});
        adj[2].add(new int[]{1, 1});
        adj[2].add(new int[]{3, 2});
        adj[2].add(new int[]{4, 2});

        adj[3].add(new int[]{2, 2});
        adj[3].add(new int[]{4, 1});

        adj[4].add(new int[]{3, 1});
        adj[4].add(new int[]{2, 2});

        Solution obj = new Solution();
        int mstWt = obj.spanningTree(V, adj);
        System.out.println("The sum of all the edge weights: " + mstWt);

    }
}
