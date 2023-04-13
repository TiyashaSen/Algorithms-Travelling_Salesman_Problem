package FinalProject_PSA.final_demo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void testSolveTsp() {
        List<CrimePoint> crimePoints = Arrays.asList(
                new CrimePoint("ID0001", 0, 0),
                new CrimePoint("ID0002", 0, 1),
                new CrimePoint("ID0003", 1, 0),
                new CrimePoint("ID0004", 1, 1)
        );

        List<Integer> tour = Main.solveTsp(crimePoints);

        assertEquals(5, tour.size(), "The size of the TSP tour should be equal to the number of crime points + 1 (to complete the cycle).");

        // Check if the tour starts and ends with the same node.
        assertEquals(tour.get(0), tour.get(tour.size() - 1), "The tour should start and end with the same node.");
    }

    @Test
    void testTourLength() {
        double[][] distanceMatrix = {
                {0, 1, 1, Math.sqrt(2)},
                {1, 0, Math.sqrt(2), 1},
                {1, Math.sqrt(2), 0, 1},
                {Math.sqrt(2), 1, 1, 0}
        };

        List<Integer> tour = Arrays.asList(0, 1, 3, 2, 0);

        double tourLength = Main.tourLength(tour, distanceMatrix);

        assertEquals(4.0, tourLength, 1e-9, "The tour length should be equal to the sum of the distances between the nodes in the tour.");
    }
}
