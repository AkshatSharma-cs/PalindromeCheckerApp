// PROBLEM 7

import java.util.*;
import java.util.concurrent.*;

class Flight {
    private final String flightId;
    private final Map<String, Boolean> seatMap; // seatNo -> booked/unbooked

    public Flight(String flightId, int seatCount) {
        this.flightId = flightId;
        this.seatMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= seatCount; i++) {
            seatMap.put("Seat-" + i, false);
        }
    }

    // Synchronized booking to prevent race conditions
    public synchronized String bookSeat(String seatNo, String userId) {
        if (!seatMap.containsKey(seatNo)) {
            return "Invalid seat number!";
        }
        if (!seatMap.get(seatNo)) {
            seatMap.put(seatNo, true);
            return "Booking confirmed for " + userId + " on " + seatNo;
        } else {
            return "Seat " + seatNo + " already booked!";
        }
    }

    public List<String> getAvailableSeats() {
        List<String> available = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : seatMap.entrySet()) {
            if (!entry.getValue()) {
                available.add(entry.getKey());
            }
        }
        return available;
    }

    public String getFlightId() {
        return flightId;
    }
}

public class AirlineReservationSystem {
    private final Map<String, Flight> flights;

    public AirlineReservationSystem() {
        flights = new ConcurrentHashMap<>();
    }

    public void addFlight(String flightId, int seatCount) {
        flights.put(flightId, new Flight(flightId, seatCount));
    }

    public String reserveSeat(String flightId, String seatNo, String userId) {
        Flight flight = flights.get(flightId);
        if (flight == null) {
            return "Flight not found!";
        }
        return flight.bookSeat(seatNo, userId);
    }

    public List<String> getAvailableSeats(String flightId) {
        Flight flight = flights.get(flightId);
        if (flight == null) {
            return Collections.emptyList();
        }
        return flight.getAvailableSeats();
    }

    // Demo
    public static void main(String[] args) {
        AirlineReservationSystem system = new AirlineReservationSystem();
        system.addFlight("AI2026", 5);

        // Simulate bookings
        System.out.println(system.reserveSeat("AI2026", "Seat-1", "user_123"));
        System.out.println(system.reserveSeat("AI2026", "Seat-1", "user_456")); // should fail
        System.out.println(system.reserveSeat("AI2026", "Seat-2", "user_789"));

        System.out.println("Available seats: " + system.getAvailableSeats("AI2026"));
    }
}
