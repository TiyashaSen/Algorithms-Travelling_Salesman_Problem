package FinalProject_PSA.final_demo;

public  class CrimePoint {
    private final String crimeID;
    private final double longitude;
    private final double latitude;

    public CrimePoint(String crimeID, double longitude, double latitude) {
        this.crimeID = crimeID;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCrimeID() {
        return crimeID;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public static final double EARTH_RADIUS = 6371; // kilometers
    
        public double distanceTo(CrimePoint other) {
            double dLat = Math.toRadians(other.latitude - this.latitude);
            double dLon = Math.toRadians(other.longitude - this.longitude);
            double lat1 = Math.toRadians(this.latitude);
            double lat2 = Math.toRadians(other.latitude);
    
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    
            return EARTH_RADIUS * c;
        }
    
}