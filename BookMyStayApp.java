import java.util.*;

/**
 * ============================================================
 * CLASS - Reservation
 * ============================================================
 *
 * Represents a booking request.
 *
 * @version 11.0
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**
 * ============================================================
 * CLASS - BookingRequestQueue
 * ============================================================
 *
 * Shared booking queue for concurrent simulation.
 *
 * @version 11.0
 */
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        synchronized (this) {
            requestQueue.offer(reservation);
        }
    }

    public Reservation getNextRequest() {
        synchronized (this) {
            return requestQueue.poll();
        }
    }

    public boolean hasPendingRequests() {
        synchronized (this) {
            return !requestQueue.isEmpty();
        }
    }
}

/**
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 *
 * Shared inventory for concurrent simulation.
 *
 * @version 11.0
 */
class RoomInventory {
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

    public synchronized boolean decrementAvailability(String roomType) {
        int current = roomAvailability.getOrDefault(roomType, 0);
        if (current > 0) {
            roomAvailability.put(roomType, current - 1);
            return true;
        }
        return false;
    }
}

/**
 * ============================================================
 * CLASS - RoomAllocationService
 * ============================================================
 *
 * Allocates rooms safely with synchronization.
 *
 * @version 11.0
 */
class RoomAllocationService {
    private Map<String, Integer> allocationCounter;

    public RoomAllocationService() {
        allocationCounter = new HashMap<>();
    }

    public synchronized void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        if (inventory.decrementAvailability(roomType)) {
            int count = allocationCounter.getOrDefault(roomType, 0) + 1;
            allocationCounter.put(roomType, count);
            String roomId = roomType + "-" + count;
            System.out.println("Booking confirmed for Guest: " +
                    reservation.getGuestName() +
                    ", Room ID: " + roomId);
        } else {
            System.out.println("No " + roomType + " rooms available for Guest: " +
                    reservation.getGuestName());
        }
    }
}

/**
 * ============================================================
 * CLASS - ConcurrentBookingProcessor
 * ============================================================
 *
 * Runnable task that processes bookings concurrently.
 *
 * @version 11.0
 */
class ConcurrentBookingProcessor implements Runnable {
    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(BookingRequestQueue bookingQueue,
                                      RoomInventory inventory,
                                      RoomAllocationService allocationService) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {
        while (bookingQueue.hasPendingRequests()) {
            Reservation reservation;
            synchronized (bookingQueue) {
                reservation = bookingQueue.getNextRequest();
            }
            if (reservation != null) {
                synchronized (inventory) {
                    allocationService.allocateRoom(reservation, inventory);
                }
            }
        }
    }
}

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * Simulates multiple users booking rooms at the same time.
 * Synchronization ensures thread safety and prevents race conditions.
 *
 * @version 11.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add sample reservations
        bookingQueue.addRequest(new Reservation("Adwi", "Single"));
        bookingQueue.addRequest(new Reservation("Kwanthani", "Double"));
        bookingQueue.addRequest(new Reservation("Kavi", "Suite"));
        bookingQueue.addRequest(new Reservation("Suruh", "Single"));

        // Create booking processor tasks
        Thread t1 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));
        Thread t2 = new Thread(new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService));

        // Start concurrent processing
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Display remaining inventory
        System.out.println("\nRemaining Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.getRoomAvailability().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
