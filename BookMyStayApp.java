import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * Confirms booking requests by assigning rooms safely
 * while ensuring inventory consistency and preventing
 * double-booking under all circumstances.
 *
 * @version 6.0
 */
public class BookMyStayApp {

    // Reservation class
    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() { return guestName; }
        public String getRoomType() { return roomType; }
    }

    // BookingRequestQueue class
    static class BookingRequestQueue {
        private Queue<Reservation> requestQueue;

        public BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }

        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
        }

        public Reservation getNextRequest() {
            return requestQueue.poll();
        }

        public boolean hasPendingRequests() {
            return !requestQueue.isEmpty();
        }
    }

    // RoomInventory class
    static class RoomInventory {
        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            initializeInventory();
        }

        private void initializeInventory() {
            roomAvailability.put("Single", 2);
            roomAvailability.put("Double", 2);
            roomAvailability.put("Suite", 1);
        }

        public Map<String, Integer> getRoomAvailability() {
            return roomAvailability;
        }

        public void decrementAvailability(String roomType) {
            int current = roomAvailability.getOrDefault(roomType, 0);
            if (current > 0) {
                roomAvailability.put(roomType, current - 1);
            }
        }
    }

    // RoomAllocationService class
    static class RoomAllocationService {
        private Set<String> allocatedRoomIds;
        private Map<String, Set<String>> assignedRoomsByType;

        public RoomAllocationService() {
            allocatedRoomIds = new HashSet<>();
            assignedRoomsByType = new HashMap<>();
        }

        public void allocateRoom(Reservation reservation, RoomInventory inventory) {
            String roomType = reservation.getRoomType();
            Map<String, Integer> availability = inventory.getRoomAvailability();

            if (availability.getOrDefault(roomType, 0) > 0) {
                String roomId = generateRoomId(roomType);

                // Ensure uniqueness
                if (!allocatedRoomIds.contains(roomId)) {
                    allocatedRoomIds.add(roomId);

                    assignedRoomsByType
                            .computeIfAbsent(roomType, k -> new HashSet<>())
                            .add(roomId);

                    // Update inventory immediately
                    inventory.decrementAvailability(roomType);

                    System.out.println("Booking confirmed for Guest: " +
                            reservation.getGuestName() +
                            ", Room ID: " + roomId);
                } else {
                    System.out.println("Error: Room ID already allocated!");
                }
            } else {
                System.out.println("No " + roomType + " rooms available for Guest: " +
                        reservation.getGuestName());
            }
        }

        private String generateRoomId(String roomType) {
            int count = assignedRoomsByType.getOrDefault(roomType, new HashSet<>()).size() + 1;
            return roomType + "-" + count;
        }
    }

    // Application entry point
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing\n");

        // Initialize inventory and queue
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Add sample reservations
        bookingQueue.addRequest(new Reservation("Anhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Initialize allocation service
        RoomAllocationService allocationService = new RoomAllocationService();

        // Process requests in FIFO order
        while (bookingQueue.hasPendingRequests()) {
            Reservation next = bookingQueue.getNextRequest();
            allocationService.allocateRoom(next, inventory);
        }
    }
}
