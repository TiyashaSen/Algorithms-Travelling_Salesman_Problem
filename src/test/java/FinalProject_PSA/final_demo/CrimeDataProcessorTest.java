package FinalProject_PSA.final_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrimeDataProcessorTest {

    @TempDir
    Path tempDir;

    private Path testFile;

    @BeforeEach
    void setUp() throws IOException {
        testFile = tempDir.resolve("test_crime_data.csv");
        try (PrintWriter writer = new PrintWriter(testFile.toFile())) {
            writer.println("ID0001,45.1234,-105.6789");
            writer.println("ID0002,45.5678,-105.9876");
        }
    }

    @Test
    void testReadCrimeDataFromFile() {
        List<CrimePoint> crimePoints = CrimeDataProcessor.readCrimeDataFromFile(testFile.toString());

        assertEquals(2, crimePoints.size(), "The number of CrimePoints should be equal to the number of lines in the test file.");

        CrimePoint crimePoint1 = crimePoints.get(0);
        assertEquals("ID0001", crimePoint1.getCrimeID());
        assertEquals(45.1234, crimePoint1.getLongitude(), 1e-9);
        assertEquals(-105.6789, crimePoint1.getLatitude(), 1e-9);

        CrimePoint crimePoint2 = crimePoints.get(1);
        assertEquals("ID0002", crimePoint2.getCrimeID());
        assertEquals(45.5678, crimePoint2.getLongitude(), 1e-9);
        assertEquals(-105.9876, crimePoint2.getLatitude(), 1e-9);
    }
}
