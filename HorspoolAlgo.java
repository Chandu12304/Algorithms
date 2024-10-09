package daaManual;

import java.util.*;

public class HorspoolAlgo {

    // Function to perform Horspool's search algorithm
    public int horspool(String haystack, String needle) {
        int n = haystack.length(); // Length of the haystack (text)
        int m = needle.length();   // Length of the needle (pattern)

        // Edge case: if needle is empty, return 0
        if (m == 0) return 0;
        // If needle is longer than haystack, return -1 (no match possible)
        if (m > n) return -1;

        // Create a bad character shift table for the needle
        Map<Character, Integer> shiftTable = new HashMap<>();
        
        // Populate the shift table: the last character gets the default shift, others get specific values
        for (int i = 0; i < m - 1; i++) {
            shiftTable.put(needle.charAt(i), m - 1 - i);
        }

        // Default shift for characters not in the needle
        int defaultShift = m;

        int i = 0; // Start from the beginning of haystack
        while (i <= n - m) {
            int j = m - 1; // Compare from the last character of the needle

            // Compare needle with the substring of haystack
            while (j >= 0 && needle.charAt(j) == haystack.charAt(i + j)) {
                j--;
            }

            // If all characters matched, return the start index
            if (j < 0) return i;

            // Move the pointer based on the bad character shift table or default shift
            char badChar = haystack.charAt(i + m - 1);
            i += shiftTable.getOrDefault(badChar, defaultShift);
        }

        // No match found
        return -1;
    }

    public static void main(String[] args) {
    	HorspoolAlgo hs = new HorspoolAlgo();
        
        String haystack = "abracadabra";
        String needle = "cad";

        int index = hs.horspool(haystack, needle);

        if (index != -1) {
            System.out.println("Needle found at index: " + index);
        } else {
            System.out.println("Needle not found.");
        }
    }
}

