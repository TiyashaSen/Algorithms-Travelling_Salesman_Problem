package FinalProject_PSA.final_demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrimeDataProcessor {

    public static final String FILE_PATH = "crime_data.csv";

    public static void main(String[] args) {
        List<CrimePoint> crimePoints = readCrimeDataFromFile(FILE_PATH);
        // Do something with the list of CrimePoints here
    }

    public static List<CrimePoint> readCrimeDataFromFile(String filePath) {
        List<CrimePoint> crimePoints = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String crimeID = fields[0];
                double longitude = Double.parseDouble(fields[1]);
                double latitude = Double.parseDouble(fields[2]);
                CrimePoint crimePoint = new CrimePoint(crimeID, longitude, latitude);
                crimePoints.add(crimePoint);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return crimePoints;
    }

}
