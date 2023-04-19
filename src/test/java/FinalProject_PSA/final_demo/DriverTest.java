package FinalProject_PSA.final_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DriverTest {

    private static List<CrimePoint> crimePoints;

    @BeforeAll
    public static void setUp() {
        crimePoints = CrimeDataProcessor.readCrimeDataFromFile("resources/crimeSampleLarge.csv");
    }

    @Test
    public void testSolveTsp() {
        List<Integer> tour = Driver.solveTsp(crimePoints, "3opt");
        assertTrue(!tour.isEmpty());
        assertEquals(crimePoints.size() +1, tour.size());
    }

    @Test
    public void testTourLength() {
        double[][] distanceMatrix = new double[3][3];
        distanceMatrix[0][1] = 1;
        distanceMatrix[0][2] = 2;
        distanceMatrix[1][0] = 1;
        distanceMatrix[1][2] = 3;
        distanceMatrix[2][0] = 2;
        distanceMatrix[2][1] = 3;
        List<Integer> tour = Arrays.asList(0, 1, 2, 0);
        double length = Driver.tourLength(tour, distanceMatrix);
        assertEquals(6.0, length, 0.01);
    }

    @Test
    public void testMinimumWeightPerfectMatching() {
        double[][] distanceMatrix = new double[3][3];
        distanceMatrix[0][1] = 1;
        distanceMatrix[0][2] = 2;
        distanceMatrix[1][0] = 1;
        distanceMatrix[1][2] = 3;
        distanceMatrix[2][0] = 2;
        distanceMatrix[2][1] = 3;
        List<Integer> oddVertices = new ArrayList<>(Arrays.asList(0, 2));
        List<Integer> matching = Driver.minimumWeightPerfectMatching(distanceMatrix, oddVertices);
        double weight = distanceMatrix[matching.get(0)][matching.get(1)] + distanceMatrix[matching.get(1)][matching.get(0)]; // calculate weight directly
        assertEquals(4.0, weight, 0.0001); // use a delta of 0.0001 for double precision
        assertEquals(Arrays.asList(0, 2), matching);
    }


}
