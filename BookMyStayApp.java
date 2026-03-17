import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * Enables guests to view available rooms and their details
 * without modifying system state. Search logic is read-only
 * and separated from booking or inventory mutation.
 *
 * @version 4.0
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

    // Centralized RoomInventory class
    public static class RoomInventory {
        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            initializeInventory();
        }

        private void initializeInventory() {
            roomAvailability.put("SingleRoom", 5);
            roomAvailability.put("DoubleRoom", 3);
            roomAvailability.put("SuiteRoom", 2);
        }

        public Map<String, Integer> getRoomAvailability() {
            return roomAvailability;
        }
    }

    // Search service (read-only)
    public static class RoomSearchService {
        public void searchAvailableRooms(RoomInventory inventory,
                                         Room singleRoom,
                                         Room doubleRoom,
                                         Room suiteRoom) {
            System.out.println("Room Search\n");

            Map<String, Integer> availability = inventory.getRoomAvailability();

            if (availability.get("SingleRoom") > 0) {
                System.out.println("Single Room:");
                singleRoom.displayRoomDetails();
                System.out.println("Available: " + availability.get("SingleRoom") + "\n");
            }

            if (availability.get("DoubleRoom") > 0) {
                System.out.println("Double Room:");
                doubleRoom.displayRoomDetails();
                System.out.println("Available: " + availability.get("DoubleRoom") + "\n");
            }

            if (availability.get("SuiteRoom") > 0) {
                System.out.println("Suite Room:");
                suiteRoom.displayRoomDetails();
                System.out.println("Available: " + availability.get("SuiteRoom") + "\n");
            }
        }
    }

    // Application entry point
    public static void main(String[] args) {
        // Initialize rooms
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Perform search
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, single, doubleR, suite);
    }
}
