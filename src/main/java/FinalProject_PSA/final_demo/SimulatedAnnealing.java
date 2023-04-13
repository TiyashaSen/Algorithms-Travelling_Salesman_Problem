package FinalProject_PSA.final_demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {
	
	static List<Integer> optimizeWithSimulatedAnnealing(List<Integer> tour, double[][] distanceMatrix) {
        double T = 1000.0;
        double coolingRate = 0.9995;
        double absoluteTemperature = 1e-8;
        int maxIterations = 100000;
        Random random = new Random();
    
        List<Integer> currentSolution = new ArrayList<>(tour);
        double currentCost = tourLength(currentSolution, distanceMatrix);
    
        List<Integer> bestSolution = new ArrayList<>(currentSolution);
        double bestCost = currentCost;
    
        int iteration = 0;
        while (T > absoluteTemperature && iteration < maxIterations) {
            List<Integer> newSolution = swapEdges(currentSolution);
            double newCost = tourLength(newSolution, distanceMatrix);
    
            if (newCost < currentCost) {
                currentSolution = new ArrayList<>(newSolution);
                currentCost = newCost;
    
                if (newCost < bestCost) {
                    bestSolution = new ArrayList<>(newSolution);
                    bestCost = newCost;
                }
            } else if (Math.exp((currentCost - newCost) / T) > random.nextDouble()) {
                currentSolution = new ArrayList<>(newSolution);
                currentCost = newCost;
            }
    
            T *= coolingRate;
            iteration++;
        }
    
        return bestSolution;
    }
    
	   static double tourLength(List<Integer> tour, double[][] distanceMatrix) {
	        double length = 0;
	        for (int i = 1; i < tour.size(); i++) {
	            length += distanceMatrix[tour.get(i - 1)][tour.get(i)];
	        }
	        length += distanceMatrix[tour.get(tour.size() - 1)][tour.get(0)];
	        return length;
	    }

    static List<Integer> swapEdges(List<Integer> tour) {
        int n = tour.size();
        Random random = new Random();
        int i = random.nextInt(n - 2) + 1;
        int j = random.nextInt(n - i - 1) + i + 1;

        List<Integer> newTour = new ArrayList<>(tour);
        Collections.reverse(newTour.subList(i, j));

        return newTour;
    }

}
