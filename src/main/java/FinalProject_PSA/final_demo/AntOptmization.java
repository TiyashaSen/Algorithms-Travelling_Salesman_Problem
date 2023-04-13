package FinalProject_PSA.final_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntOptmization {
	
	
	 public static List<Integer> optimizeWithAntColony(List<Integer> tour, double[][] distanceMatrix) {
	        System.out.println("the tour passed" + tour);
	        System.out.println("the distance matrix" + distanceMatrix);
	        int n = tour.size() - 1;
	        int numberOfAnts = 100;
	        int maxIterations = 100;
	        double alpha = 1;
	        double beta = 5;
	        double evaporationRate = 0.5;
	        double initialPheromone = 1.0 / (n * tourLength(tour, distanceMatrix));
	        double[][] pheromoneMatrix = new double[n][n];
	        double[][] visibilityMatrix = new double[n][n];

	        // Initialize pheromone and visibility matrices
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                pheromoneMatrix[i][j] = initialPheromone;
	                visibilityMatrix[i][j] = (i == j) ? 0 : 1 / distanceMatrix[i][j];
	            }
	        }

	        List<Integer> bestTour = new ArrayList<>(tour);
	        // System.out.println("the best tour"+bestTour);
	        double bestCost = tourLength(tour, distanceMatrix);

	        for (int iteration = 0; iteration < maxIterations; iteration++) {
	            List<List<Integer>> solutions = new ArrayList<>();
	            for (int ant = 0; ant < numberOfAnts; ant++) {
	                System.out.println("Step1" + ant);
	                List<Integer> currentTour = buildAntTour(distanceMatrix.length, pheromoneMatrix, visibilityMatrix,
	                        alpha, beta);
	                // System.out.println("the current tour -------" + currentTour);
	                currentTour.add(currentTour.get(0)); // complete the cycle
	                solutions.add(currentTour);

	                double currentCost = tourLength(currentTour, distanceMatrix);
	                if (currentCost < bestCost) {
	                    bestTour = new ArrayList<>(currentTour);
	                    bestCost = currentCost;
	                }
	            }

	            System.out.println("Step2");
	            // Evaporate pheromone
	            for (int i = 0; i < n; i++) {
	                for (int j = 0; j < n; j++) {
	                    pheromoneMatrix[i][j] *= evaporationRate;
	                }
	            }

	            // Deposit pheromone
	            for (List<Integer> solution : solutions) {
	                double currentCost = tourLength(solution, distanceMatrix);
	                // System.out.println("the current cost");
	                for (int i = 0; i < n; i++) {
	                    int cityA = solution.get(i);
	                    int cityB = solution.get(i + 1);
	                    pheromoneMatrix[cityA][cityB] += 1 / currentCost;
	                    pheromoneMatrix[cityB][cityA] += 1 / currentCost;
	                }
	            }
	            // System.out.println("looping");
	        }
	        // System.out.println("the best tour" + bestTour);
	        return bestTour;
	    }

	 public static List<Integer> buildAntTour(int n, double[][] pheromoneMatrix, double[][] visibilityMatrix,
	            double alpha, double beta) {
	        List<Integer> tour = new ArrayList<>();
	        boolean[] visited = new boolean[n];

	        Random random = new Random();
	        int currentCity = random.nextInt(n);
	        tour.add(currentCity);
	        visited[currentCity] = true;
	        // System.out.println( "HERE1");
	        while (tour.size() < n) {
	            // System.out.println( "HERE2");
	            double[] probabilities = new double[n];
	            double sum = 0;

	            for (int i = 0; i < n; i++) {
	                if (!visited[i]) {
	                    probabilities[i] = Math.pow(pheromoneMatrix[currentCity][i], alpha)
	                            * Math.pow(visibilityMatrix[currentCity][i], beta);
	                    sum += probabilities[i];
	                }
	            }

	            double rnd = random.nextDouble() * sum;
	            int nextCity = -1;
	            double maxProbability = -1;
	            for (int i = 0; i < n; i++) {
	                if (!visited[i] && probabilities[i] > maxProbability) {
	                    maxProbability = probabilities[i];
	                    nextCity = i;
	                }
	            }

	            if (nextCity != -1) {
	                tour.add(nextCity);
	                visited[nextCity] = true;
	                currentCity = nextCity;
	            }
	        }
	        System.out.println("HERE3");

	        return tour;
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
