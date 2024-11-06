public class NQueenProblem { 

    // Size of the board (4x4 for this case)
    final int N = 4;

    // Function to print the solution board
    void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Print 'Q' for queens and '.' for empty spaces
                if (board[i][j] == 1)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }

    // Function to check if placing a queen at board[row][col] is safe
    boolean isSafe(int board[][], int row, int col) {
        int i, j;

        // Check if there is a queen in the same column (to the left of the current position)
        for (i = col; i >= 0; i--)
            if (board[row][i] == 1)
                return false;

        // Check upper left diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check lower left diagonal
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true; // No conflicts, safe to place queen
    }

    // Recursive utility function to solve the N Queen problem
    boolean solveNQUtil(int board[][], int col) {
        // If all queens are placed, return true
        if (col >= N)
            return true;

        // Try placing a queen in all rows of the current column
        for (int i = 0; i < N; i++) {
            // If placing a queen at (i, col) is safe, place it and move to the next column
            if (isSafe(board, i, col)) {
                board[i][col] = 1; // Place the queen

                // Recur to place the remaining queens
                if (solveNQUtil(board, col + 1) == true)
                    return true;

                // If placing queen in (i, col) doesn't lead to a solution, backtrack
                board[i][col] = 0; // Remove the queen (backtrack)
            }
        }

        return false; // If the queen can't be placed in any row, return false
    }

    // Function to solve the N Queen problem
    boolean solveNQ() {
        // Initialize the board with all 0s (no queens placed initially)
        int board[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

        // Call the utility function to solve the problem
        if (solveNQUtil(board, 0) == false) {
            System.out.print("Solution does not exist");
            return false; // No solution exists
        }

        // Print the board configuration with queens placed
        printSolution(board);
        return true; // Solution exists
    }

    // Main function to execute the solution
    public static void main(String args[]) {
        NQueenProblem Queen = new NQueenProblem(); // Create an instance of NQueenProblem
        Queen.solveNQ(); // Solve the N-Queens problem
    }
}
