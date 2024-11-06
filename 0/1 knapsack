import java.util.*;

public class Knapsack {

    static int knapsack(int ind, int target, int[] w, int[] val, int[][] dp) {
        // Base case: if no items are left to consider
        if (ind == 0) {
            if (w[0] <= target) return val[0];  // If the first item's weight is less than or equal to target, include it
            return 0;  // Otherwise, return 0 since we can't include the first item
        }

        // If the target is 0, no value can be added
        if (target == 0) return 0;

        // If this subproblem is already solved, return the stored result
        if (dp[ind][target] != -1) return dp[ind][target];

        // Don't take the current item
        int notTake = knapsack(ind - 1, target, w, val, dp);

        // Take the current item, if possible
        int take = Integer.MIN_VALUE;
        if (w[ind] <= target) {
            take = knapsack(ind - 1, target - w[ind], w, val, dp) + val[ind];
        }

        // Store the result and return the maximum of taking or not taking the item
        dp[ind][target] = Math.max(take, notTake);
        return dp[ind][target];
    }

    static void findSelectedItems(int ind, int target, int[] w, int[][] dp, ArrayList<Integer> ans) {
        // Backtrack to find the selected items
        while (ind >= 0 && target > 0) {
            // If the current item is included in the optimal solution
            if (ind == 0 || dp[ind][target] != dp[ind - 1][target]) {
                ans.add(ind);  // Add the item index to the answer list
                target -= w[ind];  // Decrease the target by the weight of the selected item
            }
            ind--;  // Move to the previous item
        }
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};  // Weights of items
        int[] val = {10, 20, 30};  // Values of items
        int target = 4;  // Capacity of the knapsack
        int n = w.length;  // Number of items

        // Create dp array and initialize with -1 (indicating unvisited subproblems)
        int[][] dp = new int[n][target + 1];
        for (int[] row : dp) {
            for (int i = 0; i < row.length; i++) {
                row[i] = -1;
            }
        }

        // Call knapsack function
        System.out.println("Maximum value in knapsack = " + knapsack(n - 1, target, w, val, dp));

        // ArrayList to store the indices of the selected items
        ArrayList<Integer> ans = new ArrayList<>();

        // Backtrack to find which items are selected
        findSelectedItems(n - 1, target, w, dp, ans);

        // Print the selected items (index-based)
        System.out.print("Selected items (indices): ");
        for (int item : ans) {
            System.out.print(item + " ");
        }
    }
}
