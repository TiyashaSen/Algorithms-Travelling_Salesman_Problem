package FinalProject_PSA.final_demo;

import java.util.ArrayList;
import java.util.List;

public class OptimizeThreeOpt {
	
	  static List<Integer> optimizeThreeOpt(List<Integer> tour, double[][] distanceMatrix, int MAX_ITERATIONS) {
	        int n = tour.size();
	        int iter = 0;
	        double MIN_IMPROVEMENT = 1e-6;
	        while (iter < MAX_ITERATIONS) {
	            double oldLength = tourLength(tour, distanceMatrix);
	            double bestDelta = MIN_IMPROVEMENT;
	            int bestI = -1, bestJ = -1, bestK = -1;

	            for (int i = 0; i < n - 2; i++) {
	                for (int j = i + 1; j < n - 1; j++) {
	                    for (int k = j + 1; k < n; k++) {
	                        double delta = (distanceMatrix[tour.get(i)][tour.get(j)]
	                                + distanceMatrix[tour.get(j)][tour.get(k)]
	                                + distanceMatrix[tour.get(k)][tour.get((i + 1) % n)]) -
	                                (distanceMatrix[tour.get(i)][tour.get((i + 1) % n)]
	                                        + distanceMatrix[tour.get(j)][tour.get((j + 1) % n)]
	                                        + distanceMatrix[tour.get(k)][tour.get((k + 1) % n)]);

	                        if (delta < bestDelta) {
	                            bestDelta = delta;
	                            bestI = i;
	                            bestJ = j;
	                            bestK = k;
	                        }
	                    }
	                }
	            }

	            if (bestDelta < 0) {
	                List<Integer> newTour = new ArrayList<>(n);
	                for (int a = 0; a <= bestI; a++) {
	                    newTour.add(tour.get(a));
	                }
	                for (int a = bestJ; a > bestI; a--) {
	                    newTour.add(tour.get(a));
	                }
	                for (int a = bestK; a > bestJ; a--) {
	                    newTour.add(tour.get(a));
	                }
	                for (int a = bestK + 1; a < n; a++) {
	                    newTour.add(tour.get(a));
	                }
	                tour.clear();
	                tour.addAll(newTour);
	                iter++;

	            } else {
	                iter++;
	            }
	        }
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
