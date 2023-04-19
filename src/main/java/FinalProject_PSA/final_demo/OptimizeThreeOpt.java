package FinalProject_PSA.final_demo;

import java.util.ArrayList;
import java.util.List;

public class OptimizeThreeOpt {
	
	public static List<Integer> optimizeThreeOpt(List<Integer> tour, double[][] dist) {
	    int n = tour.size();
	    boolean innerImproved = false;
	    boolean improved = true;
	    int counter = 0;
	    while (improved) {
	        improved = false;
	        for (int i = 0; i < n - 2; i++) {
	            for (int j = i + 2; j < n - 1; j++) {
	                for (int k = j + 2; k < n; k++) {
	                    List<Integer> newTour = reverse(tour, i + 1, j, j + 1, k);
	                    int newDist = calculateDistance(newTour, dist);

	                    if (newDist < calculateDistance(tour, dist)) {
	                        tour = newTour;
	                        improved = true;
	                        innerImproved = true;
	                    }
	                }
	            }
	            System.out.println("the outer for loop");
	        }
	        if (!innerImproved) {
	            counter++;
	        }
	        if (counter >= n) {
	            break;
	        }
	        improved = improved || innerImproved;
	        innerImproved = false;
	    }
	    System.out.println("the tour"+tour);
	    return tour;
	}


	private static List<Integer> reverse(List<Integer> tour, int i, int j, int k, int l) {
	    List<Integer> newTour = new ArrayList<>(tour);

	    // Reverse the sequence from i+1 to j
	    int x = i + 1, y = j;
	    while (x < y) {
	        int temp = newTour.get(x);
	        newTour.set(x, newTour.get(y));
	        newTour.set(y, temp);
	        x++;
	        y--;
	    }

	    // Reverse the sequence from j+1 to k
	    x = j + 1; y = k;
	    while (x < y) {
	        int temp = newTour.get(x);
	        newTour.set(x, newTour.get(y));
	        newTour.set(y, temp);
	        x++;
	        y--;
	    }

	    // Reverse the sequence from k+1 to l
	    x = k + 1; y = l;
	    while (x < y) {
	        int temp = newTour.get(x);
	        newTour.set(x, newTour.get(y));
	        newTour.set(y, temp);
	        x++;
	        y--;
	    }

	    return newTour;
	}

	private static int calculateDistance(List<Integer> tour, double[][] dist) {
	    int distance = 0;
	    for (int i = 0; i < tour.size() - 1; i++) {
	        distance += dist[tour.get(i)][tour.get(i + 1)];
	    }
	    distance += dist[tour.get(tour.size() - 1)][tour.get(0)];
	    return distance;
	}
	
static double tourLength(List<Integer> tour, double[][] distanceMatrix) {
double length = 0;
for (int i = 1; i < tour.size(); i++) {

length += distanceMatrix[tour.get(i - 1)][tour.get(i)];

}

 length += distanceMatrix[tour.get(tour.size() - 1)][tour.get(0)];

return length;
}


}
