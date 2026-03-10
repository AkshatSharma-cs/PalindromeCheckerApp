// PROBLEM 10

import java.util.*;

class Driver {
    private final String driverId;
    private double latitude;
    private double longitude;
    private boolean available;

    public Driver(String driverId, double latitude, double longitude) {
        this.driverId = driverId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = true;
    }

    public String getDriverId() { return driverId; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public boolean isAvailable() { return available; }

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

public class RideSharingSystem {
    private final Map<String, Driver> drivers; // driverId -> Driver

    public RideSharingSystem() {
        drivers = new HashMap<>();
    }

    // Register driver
    public void registerDriver(String driverId, double latitude, double longitude) {
        drivers.put(driverId, new Driver(driverId, latitude, longitude));
    }

    // Update driver location
    public void updateDriverLocation(String driverId, double latitude, double longitude) {
        Driver driver = drivers.get(driverId);
        if (driver != null) {
            driver.setLocation(latitude, longitude);
        }
    }

    // Find nearest available driver
    public String findNearestDriver(double riderLat, double riderLon) {
        Driver nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : drivers.values()) {
            if (driver.isAvailable()) {
                double dist = calculateDistance(riderLat, riderLon, driver.getLatitude(), driver.getLongitude());
                if (dist < minDistance) {
                    minDistance = dist;
                    nearest = driver;
                }
            }
        }

        if (nearest != null) {
            nearest.setAvailable(false); // assign driver
            return "Matched with driver " + nearest.getDriverId() + " (distance: " + String.format("%.2f", minDistance) + " km)";
        }
        return "No available drivers nearby.";
    }

    // Haversine formula for distance
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371; // Earth radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    // Demo
    public static void main(String[] args) {
        RideSharingSystem system = new RideSharingSystem();

        // Register drivers
        system.registerDriver("driver_001", 12.9716, 77.5946); // Bangalore
        system.registerDriver("driver_002", 13.0827, 80.2707); // Chennai
        system.registerDriver("driver_003", 19.0760, 72.8777); // Mumbai

        // Rider in Chennai
        System.out.println(system.findNearestDriver(13.05, 80.27));
        // Rider in Bangalore
        System.out.println(system.findNearestDriver(12.97, 77.60));
    }
}
