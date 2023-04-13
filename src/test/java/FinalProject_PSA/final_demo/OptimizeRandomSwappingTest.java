package FinalProject_PSA.final_demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimizeRandomSwappingTest {

    @Test
    public void testTourLength() {
        double[][] distanceMatrix = {
            {0, 1, 2},
            {1, 0, 1},
            {2, 1, 0}
        };
        List<Integer> tour = Arrays.asList(0, 1, 2);

        double length = OptimizeRandomSwapping.tourLength(tour, distanceMatrix);

        assertEquals(4.0, length);
    }

    @Test
    public void testRandomSwapOptimization() {
        double[][] distanceMatrix = {
            {0, 1, 2},
            {1, 0, 1},
            {2, 1, 0}
        };
        List<Integer> tour = Arrays.asList(0, 1, 2);
        int iterations = 100;

        List<Integer> optimizedTour = OptimizeRandomSwapping.randomSwapOptimization(tour, distanceMatrix, iterations);

        assertTrue(isValidTour(optimizedTour, tour));
        assertTrue(optimizedTour.size() == tour.size());

        double optimizedTourLength = OptimizeRandomSwapping.tourLength(optimizedTour, distanceMatrix);
        double initialTourLength = OptimizeRandomSwapping.tourLength(tour, distanceMatrix);

        assertTrue(optimizedTourLength <= initialTourLength);
    }

    private boolean isValidTour(List<Integer> optimizedTour, List<Integer> originalTour) {
        List<Integer> originalCopy = new ArrayList<>(originalTour);
        for (Integer city : optimizedTour) {
            if (originalCopy.contains(city)) {
                originalCopy.remove(city);
            } else {
                return false;
            }
        }
        return originalCopy.isEmpty();
    }
}
