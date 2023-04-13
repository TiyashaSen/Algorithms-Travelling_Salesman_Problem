package FinalProject_PSA.final_demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OptimizeRandomSwapping {
	
	 static List<Integer> randomSwapOptimization(List<Integer> tour, double[][] distanceMatrix, int iterations) {
	        List<Integer> optimizedTour = new ArrayList<>(tour);
	        double currentTourLength = tourLength(optimizedTour, distanceMatrix);
	        Random random = new Random();

	        for (int i = 0; i < iterations; i++) {
	            // Randomly select two indices to swap
	            int index1 = random.nextInt(optimizedTour.size() - 1) + 1;
	            int index2 = random.nextInt(optimizedTour.size() - 1) + 1;

	            // Swap cities
	            Collections.swap(optimizedTour, index1, index2);

	            // Calculate new tour length
	            double newTourLength = tourLength(optimizedTour, distanceMatrix);

	            // If the new tour length is shorter, accept the new tour; otherwise, revert the swap
	            if (newTourLength < currentTourLength) {
	                currentTourLength = newTourLength;
	            } else {
	                Collections.swap(optimizedTour, index1, index2);
	            }
	        }

	        return optimizedTour;
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
