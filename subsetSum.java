import java.util.*;

class TUF {
    // Function to check if there exists a subset with a given target sum and store the elements
    static boolean subsetSumToK(int n, int k, int[] arr, ArrayList<Integer> ans) {
        // Create a boolean DP table with dimensions [n][k+1]
        boolean dp[][] = new boolean[n][k + 1];
        
        // Initialize the first row of the DP table
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // Initialize the first column of the DP table
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        // Fill in the DP table using bottom-up approach
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                // Calculate if the current target can be achieved without taking the current element
                boolean notTaken = dp[ind - 1][target];
                
                // Calculate if the current target can be achieved by taking the current element
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[ind - 1][target - arr[ind]];
                }
                
                // Store the result in the DP table
                dp[ind][target] = notTaken || taken;
            }
        }

        // If no subset sum found, return false
        if (!dp[n - 1][k]) {
            return false;
        }

        // Backtrack to find the elements contributing to the sum
        int i = n - 1;
        int j = k;
        while (i > 0 && j > 0) {
            // If the current cell is not the same as the one above it, it means the element was included
            if (dp[i][j] != dp[i - 1][j]) {
                ans.add(arr[i]); // Add the element to the result
                j -= arr[i]; // Reduce the target by the value of the element added
            }
            i--; // Move to the previous row
        }

        // Check for the first element in case it's part of the subset
        if (j > 0) {
            ans.add(arr[0]);
        }

        return true;
    }

    public static void main(String args[]) {
        int arr[] = { 1, 2, 3, 4 };
        int k = 4;
        int n = arr.length;

        // ArrayList to store the elements of the subset
        ArrayList<Integer> ans = new ArrayList<>();

        // Check if there exists a subset with the given target sum
        if (subsetSumToK(n, k, arr, ans)) {
            System.out.println("Subset with the given target found: " + ans);
        } else {
            System.out.println("Subset with the given target not found");
        }
    }
}
