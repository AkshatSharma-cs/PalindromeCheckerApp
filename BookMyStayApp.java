import java.util.*;

/**
 * ============================================================
 * CLASS - InvalidBookingException
 * ============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * Custom exception representing invalid booking scenarios.
 * Domain-specific exceptions make error handling cleaner.
 *
 * @version 9.0
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * ============================================================
 * CLASS - RoomInventory
 * ============================================================
 *
 * Simplified inventory for validation purposes.
 * (Reused from earlier use cases.)
 *
 * @version 9.0
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
}

/**
 * ============================================================
 * CLASS - ReservationValidator
 * ============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * Validates booking requests before processing.
 * Centralizes validation rules to avoid duplication.
 *
 * @version 9.0
 */
class ReservationValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.getRoomAvailability().containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (inventory.getRoomAvailability().get(roomType) <= 0) {
            throw new InvalidBookingException("No " + roomType + " rooms available.");
        }
    }
}

/**
 * ============================================================
 * MAIN CLASS - BookMyStayApp
 * ============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * Description:
 * Demonstrates how user input is validated before booking.
 * The system accepts input, validates it, and handles errors gracefully.
 *
 * @version 9.0
 */
public class BookMyStayApp {
    public static void main(String[] args) {
        System.out.println("Booking Validation\n");

        Scanner scanner = new Scanner(System.in);
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();

        try {
            // Accept user input
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(guestName, roomType, inventory);

            // If validation passes
            System.out.println("Booking request accepted for Guest: " +
                    guestName + ", Room Type: " + roomType);

        } catch (InvalidBookingException e) {
            // Handle domain-specific validation errors
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
