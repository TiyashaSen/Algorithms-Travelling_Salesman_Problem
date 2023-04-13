package FinalProject_PSA.final_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AntOptmizationTest {

    private double[][] distanceMatrix;

    @BeforeEach
    void setUp() {
        distanceMatrix = new double[][] {
                {0, 1, 2, 3},
                {1, 0, 1, 2},
                {2, 1, 0, 1},
                {3, 2, 1, 0}
        };
    }

    @Test
    void testTourLength() {
        List<Integer> tour = Arrays.asList(0, 1, 2, 3, 0);
        double expectedTourLength = 6.0;
        double actualTourLength = AntOptmization.tourLength(tour, distanceMatrix);
        assertEquals(expectedTourLength, actualTourLength, 1e-9, "The tour length should be equal to the expected value.");
    }
}
