#include <iostream>
#include <climits> // for INT_MAX

using namespace std;

#define INT_MAX 999999
int n = 4; // Number of cities

// Adjacency Matrix defining the distances between cities
int dist[4][4] = {
    {0, 20, 42, 25},
    {20, 0, 30, 34},
    {42, 30, 0, 10},
    {25, 34, 10, 0}
};

// Mask for when all cities have been visited
int VISITED_ALL = (1 << n) - 1;

// Function to solve the Traveling Salesman Problem
int tsp(int mask, int pos) {
    if (mask == VISITED_ALL) {
        return dist[pos][0]; // Return to the starting city
    }

    int ans = INT_MAX;

    // Try to go to an unvisited city
    for (int city = 0; city < n; city++) {
        if ((mask & (1 << city)) == 0) { // Check if the city has not been visited
            int newAns = dist[pos][city] + tsp(mask | (1 << city), city);
            ans = min(ans, newAns);
        }
    }
    return ans;
}

int main() {
    cout << "Minimum travel cost: " << tsp(1, 0) << endl;
    return 0;
}
