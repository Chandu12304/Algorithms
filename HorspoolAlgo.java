class AWQ {
    static int NO_OF_CHARS = 256;  // Number of possible ASCII characters

    // Utility function to get the maximum of two numbers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Function to fill the bad character heuristic array for the pattern
    static void badCharHeuristic(char[] str, int size, int badchar[]) {
        // Initialize all occurrences as -1
        for (int i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;

        // Fill the actual value of the last occurrence of a character in the pattern
        for (int i = 0; i < size; i++)
            badchar[(int) str[i]] = i;
    }

    // Function to search for pattern occurrences in the text using the Boyer-Moore algorithm
    static void search(char txt[], char pat[]) {
        int m = pat.length;  // Length of the pattern
        int n = txt.length;  // Length of the text
        int badchar[] = new int[NO_OF_CHARS];  // Array to store bad character heuristic values

        // Preprocess the pattern to fill the bad character heuristic array
        badCharHeuristic(pat, m, badchar);

        int s = 0;  // Initialize the shift of the pattern with respect to text
        while (s <= (n - m)) {  // Loop to slide the pattern over the text
            int j = m - 1;  // Start matching from the end of the pattern

            // Move j to the left while characters in pattern and text are matching
            while (j >= 0 && pat[j] == txt[s + j])
                j--;

            // If the pattern is found
            if (j < 0) {
                System.out.println("Pattern occurs at shift = " + s);
                return;  // Exit the function after finding the first occurrence
            } else {
                // Shift the pattern based on the bad character rule
                s += j - badchar[txt[s + j]];
            }
        }
    }

    public static void main(String[] args) {
        char txt[] = "ABAAABCD".toCharArray();  // Text to search within
        char pat[] = "BCD".toCharArray();       // Pattern to search for
        search(txt, pat);  // Call the search function
    }
}
