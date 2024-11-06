import java.util.*;

public class MST {
    static class DisjointSet {
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

    // Function to find sum of weights of edges of the Minimum Spanning Tree.
    public static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        List<int[]> edges = new ArrayList<>();

        // Collect edges directly as [weight, src, dest] for sorting
        for (int i = 0; i < V; i++) {
            for (ArrayList<Integer> neighbor : adj.get(i)) {
                int adjNode = neighbor.get(0);
                int wt = neighbor.get(1);
                edges.add(new int[]{wt, i, adjNode});
            }
        }

        // Sort edges based on weights
        edges.sort(Comparator.comparingInt(a -> a[0]));

        DisjointSet ds = new DisjointSet(V);
        int mstWt = 0;

        for (int[] edge : edges) {
            int wt = edge[0];
            int u = edge[1];
            int v = edge[2];

            if (ds.findUPar(u) != ds.findUPar(v)) {
                mstWt += wt;
                ds.unionBySize(u, v);
            }
        }

        return mstWt;
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        int[][] edges = {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            ArrayList<Integer> tmp1 = new ArrayList<>();
            ArrayList<Integer> tmp2 = new ArrayList<>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

        int mstWt = spanningTree(V, adj);
        System.out.println("The sum of all the edge weights: " + mstWt);
    }
}
