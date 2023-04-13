package FinalProject_PSA.final_demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimulatedAnnealingTest {

    @Test
    public void testTourLength() {
        double[][] distanceMatrix = {
            {0, 1, 2},
            {1, 0, 1},
            {2, 1, 0}
        };
        List<Integer> tour = Arrays.asList(0, 1, 2);

        double length = SimulatedAnnealing.tourLength(tour, distanceMatrix);

        assertEquals(4.0, length);
    }

    @Test
    public void testOptimizeWithSimulatedAnnealing() {
        double[][] distanceMatrix = {
            {0, 1, 2},
            {1, 0, 1},
            {2, 1, 0}
        };
        List<Integer> tour = Arrays.asList(0, 1, 2);

        List<Integer> optimizedTour = SimulatedAnnealing.optimizeWithSimulatedAnnealing(tour, distanceMatrix);

        assertTrue(isValidTour(optimizedTour, tour));
        assertTrue(optimizedTour.size() == tour.size());

        double optimizedTourLength = SimulatedAnnealing.tourLength(optimizedTour, distanceMatrix);
        double initialTourLength = SimulatedAnnealing.tourLength(tour, distanceMatrix);

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
