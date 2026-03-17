/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * Demonstrates room initialization using domain models
 * without centralized inventory management.
 *
 * Availability is represented using simple variables
 * to highlight limitations.
 *
 * @version 2.1
 */
public class BookMyStayApp {

    // Abstract Room class
    public static abstract class Room {
        protected int numberOfBeds;
        protected int squareFeet;
        protected double pricePerNight;

        public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
            this.numberOfBeds = numberOfBeds;
            this.squareFeet = squareFeet;
            this.pricePerNight = pricePerNight;
        }

        public void displayRoomDetails() {
            System.out.println("Beds: " + numberOfBeds);
            System.out.println("Size: " + squareFeet + " sqft");
            System.out.println("Price per night: " + pricePerNight);
        }
    }

    // Concrete room classes
    public static class SingleRoom extends Room {
        public SingleRoom() { super(1, 250, 1500.0); }
    }

    public static class DoubleRoom extends Room {
        public DoubleRoom() { super(2, 400, 2500.0); }
    }

    public static class SuiteRoom extends Room {
        public SuiteRoom() { super(3, 750, 5000.0); }
    }

    // Application entry point
    public static void main(String[] args) {
        System.out.println("Hotel Room Initialization\n");

        // Availability variables
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Initialize room objects
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display details
        // all details
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable + "\n");

        System.out.println("Double Room:");
        doubleR.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable + "\n");

        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable + "\n");
    }
}
