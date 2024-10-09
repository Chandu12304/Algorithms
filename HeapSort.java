package daaManual;

import java.util.*;

public class HeapSort {

    // Heapify function to maintain the heap property
    public void heapify(int arr[], int n, int i) {
        int l = 2 * i + 1;  // Left child index
        int r = 2 * i + 2;  // Right child index
        int m = i;          // Initialize largest as root

        // Check if left child exists and is greater than root
        if (l < n && arr[l] > arr[m]) {
            m = l;
        }

        // Check if right child exists and is greater than largest
        if (r < n && arr[r] > arr[m]) {
            m = r;
        }

        // If the largest is not the root, swap it and continue heapifying
        if (m != i) {
            int temp = arr[m];
            arr[m] = arr[i];
            arr[i] = temp;
            
            heapify(arr, n, m);  // Recursively heapify the affected subtree
        }
    }

    // Function to build a heap from an array
    public void buildHeap(int arr[], int n) {
        // Start from the last non-leaf node and move up
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    // Function to sort an array using Heap Sort
    public void heapSort(int arr[], int n) {
        // Build a max heap
        buildHeap(arr, n);

        // Extract elements from the heap one by one
        for (int i = n - 1; i >= 0; i--) {
            // Move the current root to the end
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            // Call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // Main function to test heap sort
    public static void main(String[] args) {
        HeapSort hs = new HeapSort();

        int arr[] = {12, 11, 13, 5, 6, 7};
        int n = arr.length;

        System.out.println("Original array:");
        System.out.println(Arrays.toString(arr));

        hs.heapSort(arr, n);

        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(arr));
    }
}
