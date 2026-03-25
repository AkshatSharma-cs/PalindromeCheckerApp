import java.util.*;

/**
 * ============================================================
 * CLASS - AddOnService
 * ============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * Represents an optional service that can be added
 * to a confirmed reservation (e.g., Breakfast, Spa).
 *
 * @version 7.0
 */
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() { return serviceName; }
    public double getCost() { return cost; }
}

/**
 * ============================================================
 * CLASS - AddOnServiceManager
 * ============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * Manages optional services associated with reservations.
 * Supports attaching multiple services to a single reservation.
 *
 * @version 7.0
 */
class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceByReservation;

    public AddOnServiceManager() {
        serviceByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        serviceByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        List<AddOnService> services = serviceByReservation.getOrDefault(reservationId, new ArrayList<>());
        double total = 0.0;
        for (AddOnService s : services) {
            total += s.getCost();
        }
        return total;
    }
}

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * Demonstrates how optional services can be attached
 * to a confirmed booking. Services are added after
 * room allocation and do not affect inventory.
 *
 * @version 7.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Add-on Service Selection\n");

        // Example reservation ID (from UC6 allocation)
        String reservationId = "Single-1";

        // Initialize service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services to reservation
        manager.addService(reservationId, new AddOnService("Breakfast", 500.0));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000.0));

        // Calculate total add-on cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        // Display result
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-on Cost: " + totalCost);
    }
}
