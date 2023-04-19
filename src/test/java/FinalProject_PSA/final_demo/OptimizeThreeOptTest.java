package FinalProject_PSA.final_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import FinalProject_PSA.final_demo.OptimizeThreeOpt;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import org.junit.jupiter.api.Test;

public class OptimizeThreeOptTest {
	@Test
	public void testTourLength() {
		double[][] distanceMatrix = {

        double length = OptimizeThreeOpt.tourLength(tour, distanceMatrix);

        assertEquals(4.0, length);
    }

	@Test
	void verifyThreeOpt() {

		double[][] distanceMatrix = {

				{ 0, 1, 2 },

				{ 1, 0, 1 },

				{ 2, 1, 0 }

		};

		List<Integer> tour = Arrays.asList(0, 1, 2);

		int maxIterations = 100;

		List<Integer> tourCopy = new ArrayList<>(tour);

		List<Integer> optimizedTour = OptimizeThreeOpt.optimizeThreeOpt(tourCopy, distanceMatrix, maxIterations);

		assertTrue(isValidTour(optimizedTour, tour));

		assertTrue(optimizedTour.size() == tour.size());

		double optimizedTourLength = OptimizeThreeOpt.tourLength(optimizedTour, distanceMatrix);

		double initialTourLength = OptimizeThreeOpt.tourLength(tour, distanceMatrix);

		assertTrue(optimizedTourLength >= initialTourLength);

	}
}