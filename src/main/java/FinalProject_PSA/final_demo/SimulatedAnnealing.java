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
	        swapEdges(currentSolution);
	        double newCost = tourLength(currentSolution, distanceMatrix);

	        if (newCost < currentCost) {
	            currentCost = newCost;

	            if (newCost < bestCost) {
	                bestSolution = new ArrayList<>(currentSolution);
	                bestCost = newCost;
	            }
	        } else if (Math.exp((currentCost - newCost) / T) > random.nextDouble()) {
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

	static void swapEdges(List<Integer> tour) {
	    int n = tour.size();
	    Random random = new Random();
	    int i = random.nextInt(n - 2) + 1;
	    int j = random.nextInt(n - i - 1) + i + 1;

	    int temp = tour.get(i);
	    tour.set(i, tour.get(j));
	    tour.set(j, temp);
	}



}
