package FinalProject_PSA.final_demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Driver {
    static String algoType;


    private static final int MAX_ITERATIONS = 100;
      private static final String FILE_PATH = "resources/CrimeSampleLarge.csv";

    public static void travellingSalesman(String algoType) {
        List <CrimePoint> crimePoints = CrimeDataProcessor.readCrimeDataFromFile(FILE_PATH);
        List <Integer> tour = solveTsp(crimePoints, algoType);
        System.out.println("TSP tour: " + tour.size());


        Graph graph = new SingleGraph("TSP Tour");
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");

        Set < Integer > addedNodes = new HashSet < > ();

        double minLatitude = crimePoints.stream().mapToDouble(CrimePoint::getLatitude).min().orElse(0);
        double maxLatitude = crimePoints.stream().mapToDouble(CrimePoint::getLatitude).max().orElse(0);
        double minLongitude = crimePoints.stream().mapToDouble(CrimePoint::getLongitude).min().orElse(0);
        double maxLongitude = crimePoints.stream().mapToDouble(CrimePoint::getLongitude).max().orElse(0);

        for (int i = 0; i < tour.size(); i++) {
            if (!addedNodes.contains(tour.get(i))) {
                CrimePoint crimePoint = crimePoints.get(tour.get(i));
                Node node = graph.addNode(String.valueOf(tour.get(i)));
                node.setAttribute("ui.label", String.valueOf(tour.get(i)));

                // Normalize latitude and longitude values to fit within the range [-1, 1]
                double normalizedLatitude = 3 * (crimePoint.getLatitude() - minLatitude) / (maxLatitude - minLatitude) - 1;
                double normalizedLongitude = 3 * (crimePoint.getLongitude() - minLongitude) / (maxLongitude - minLongitude) - 1;

                node.setAttribute("xy", normalizedLatitude, normalizedLongitude);
                node.setAttribute("layout.frozen");
                addedNodes.add(tour.get(i));
            }
        }

        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph.display();

        graph.setAttribute("ui.stylesheet", "node{\n" +
            " size: 5px, 5px;\n" +
            " fill-color: black;\n" +
            " text-mode: normal; \n" +
            "}" +
            "edge.highlight{\n" +
            " fill-color: red;\n" +
            " size: 2.5px, 2.5px;" +
            "}"
        );
        System.out.println("drawing the graph");
        new Thread(() -> {
            if (tour.size() > 1) {
                for (int i = 0; i < tour.size() - 1; i++) {
                    graph.addEdge(String.format("%d-%d", tour.get(i), tour.get(i + 1)), String.valueOf(tour.get(i)), String.valueOf(tour.get(i + 1)));

                    try {
                        Thread.sleep(1000); // 1000 milliseconds = 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // Connect the last vertex to the first one to complete the tour
                graph.addEdge(String.format("%d-%d", tour.get(tour.size() - 1), tour.get(0)), String.valueOf(tour.get(tour.size() - 1)), String.valueOf(tour.get(0)));
            }
        }).start();

    }



    static List < Integer > solveTsp(List < CrimePoint > crimePoints, String algoType) {
    	//OptimizeThreeOpt optimizeThreeOp = new OptimizeThreeOpt();
        int n = crimePoints.size();
        int[] nearestNeighbor = new int[n];
        boolean[] visited = new boolean[n];
        double[][] distanceMatrix = new double[n][n];

        // Create distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distanceMatrix[i][j] = crimePoints.get(i).distanceTo(crimePoints.get(j));
            }
        }

        // Find nearest neighbor for each city
        for (int i = 0; i < n; i++) {
            double minDistance = Double.MAX_VALUE;
            int nearestNeighborIndex = -1;
            for (int j = 0; j < n; j++) {
                if (i != j && distanceMatrix[i][j] < minDistance) {
                    minDistance = distanceMatrix[i][j];
                    nearestNeighborIndex = j;
                }
            }
            if (nearestNeighborIndex != -1) { // Add this condition
                nearestNeighbor[i] = nearestNeighborIndex;
            }
            
           
        }

        // Create minimum spanning tree
        List < Integer > mst = new ArrayList < > ();
        visited[0] = true;
        double totalCostMst = 0; // Initialize a variable to accumulate the total cost
        for (int i = 1; i < n; i++) {
            double minDistance = Double.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < n; j++) {
                if (visited[j]) {
                    for (int k = 0; k < n; k++) {
                        if (!visited[k] && distanceMatrix[j][k] < minDistance) {
                            minDistance = distanceMatrix[j][k];
                            minIndex = k;
                        }
                    }
                }
            }
            visited[minIndex] = true;
            mst.add(minIndex);
            totalCostMst += minDistance; // Add the cost of the edge to the total cost

        }

        System.out.println("Minimum Spanning Tree: " + mst.size());
        System.out.println("Total Cost MST: " + totalCostMst);

        // Add edges from minimum weight perfect matching to MST
        List < Integer > oddVertices = new ArrayList < > ();
        for (int i = 0; i < n; i++) {
            if (mst.contains(i)) {
                continue;
            }
            oddVertices.add(i);
        }
        List < Integer > matching = minimumWeightPerfectMatching(distanceMatrix, oddVertices);
        mst.addAll(matching);

        // Create Eulerian tour from MST and perfect matching
        List < Integer > eulerianTour = new ArrayList < > ();
        for (int i = 0; i < n; i++) {
            eulerianTour.add(mst.get(i));
            if (matching.contains(i)) {
                eulerianTour.add(i);
            }
        }

        //hamiltonian cycle
        List < Integer > tspSolution = new ArrayList < > ();
        boolean[] added = new boolean[n];
        for (int i: eulerianTour) {
            if (!added[i]) {
                tspSolution.add(i);
                added[i] = true;
            }
        }

        // Add the starting node to complete the cycle

        double eulerianTourCost = tourLength(eulerianTour, distanceMatrix);
        System.out.println("the eulerian tour cost" + eulerianTourCost);
        tspSolution.add(tspSolution.get(0));
        List < Integer > tourList = new ArrayList < > ();

        if (algoType == "3opt") {
        	System.out.println("hitting here");
            tourList = OptimizeThreeOpt.optimizeThreeOpt(tspSolution, distanceMatrix, 1000);
        } else if (algoType == "randomSwapping") {
            tourList = OptimizeRandomSwapping.randomSwapOptimization(tspSolution, distanceMatrix, 1000);
        } else if (algoType == "simulatedAnnealing") {
            tourList = SimulatedAnnealing.optimizeWithSimulatedAnnealing(tspSolution, distanceMatrix);

        } else if (algoType == "antColony") {
            tourList = AntOptmization.optimizeWithAntColony(tspSolution, distanceMatrix);
        }
        //double totalCost = tourLength(tourListSa, distanceMatrix); 
        //double totalCost = tourLength(tourListAo, distanceMatrix); 
        double totalCost = tourLength(tourList, distanceMatrix);
        //double totalCost = tourLength(tourListRs, distanceMatrix); 
        //System.out.println("the total cost of tsp"+ totalCost);
        //System.out.println("the total cost of tsp"+ totalCost);
        //        System.out.println("the total cost of tsp"+ totalCost);
        System.out.println("the total cost of tsp"+ totalCost);
        //        System.out.println("Tour List"+ tourList);
        return tourList;
    }

    static List < Integer > minimumWeightPerfectMatching(double[][] distanceMatrix, List < Integer > oddVertices) {
        List < Integer > matching = new ArrayList < > ();
        while (!oddVertices.isEmpty()) {
            int i = oddVertices.remove(0);
            double minDistance = Double.MAX_VALUE;
            int minIndex = -1;
            for (int j: oddVertices) {
                if (distanceMatrix[i][j] < minDistance) {
                    minDistance = distanceMatrix[i][j];
                    minIndex = j;
                }
            }
            oddVertices.remove(Integer.valueOf(minIndex));
            matching.add(i);
            matching.add(minIndex);
        }
        return matching;
    }

    static double tourLength(List < Integer > tour, double[][] distanceMatrix) {
    	
    	  if (tour.isEmpty()) {
    	        return 0;
    	    }
        double length = 0;
        for (int i = 1; i < tour.size(); i++) {
            length += distanceMatrix[tour.get(i - 1)][tour.get(i)];
        }
        length += distanceMatrix[tour.get(tour.size()-1)][tour.get(0)];
        return length;
    }
}