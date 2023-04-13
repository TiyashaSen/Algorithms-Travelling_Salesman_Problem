package FinalProject_PSA.final_demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CrimePointTest {

    @Test
    public void testConstructor() {
        CrimePoint crimePoint = new CrimePoint("crime123", 12.9715987, 77.5945627);
        
        assertNotNull(crimePoint);
        assertEquals("crime123", crimePoint.getCrimeID());
        assertEquals(12.9715987, crimePoint.getLongitude());
        assertEquals(77.5945627, crimePoint.getLatitude());
    }

    @Test
    public void testDistanceTo() {
        CrimePoint crimePoint1 = new CrimePoint("crime123", 12.9715987, 77.5945627);
        CrimePoint crimePoint2 = new CrimePoint("crime456", 12.9716000, 77.5945700);

        double distance = crimePoint1.distanceTo(crimePoint2);

        double expectedDistance = 0.000638;
        double delta = 0.0001; // tolerance
        assertEquals(expectedDistance, distance, delta);
    }

    @Test
    public void testDistanceToSamePoint() {
        CrimePoint crimePoint = new CrimePoint("crime123", 12.9715987, 77.5945627);
        double distance = crimePoint.distanceTo(crimePoint);

        assertEquals(0, distance);
    }

    @Test
    public void testDistanceToAntipodes() {
        CrimePoint crimePoint1 = new CrimePoint("crime123", 0, 0);
        CrimePoint crimePoint2 = new CrimePoint("crime456", 0, 180);

        double distance = crimePoint1.distanceTo(crimePoint2);

        double expectedDistance = CrimePoint.EARTH_RADIUS * Math.PI;
        double delta = 0.001; // tolerance
        assertEquals(expectedDistance, distance, delta);
    }
}
