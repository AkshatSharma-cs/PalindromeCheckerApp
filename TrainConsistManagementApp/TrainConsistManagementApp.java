import java.util.ArrayList;
import java.util.List;

/**
 * ================================================================
 * MAIN CLASS - TrainConsistManagementApp
 * ================================================================
 *
 * Use Case 10: Count Total Seats in Train
 *
 * Description:
 * This class aggregates seating capacity of all bogies
 * into a single total using Stream reduce().
 *
 * At this stage, the application:
 * - Creates bogie list
 * - Maps bogies to capacity
 * - Reduces values into total
 * - Displays total seat count
 *
 * This maps aggregation logic using reduce().
 *
 * @author Akshat
 * @version 10.0
 */
public class TrainConsistManagementApp {

    // Reusing Bogie model
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return name + " -> " + capacity;
        }
    }

    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println(" UC10 - Count Total Seats in Train ");
        System.out.println("===========================================\n");

        // Create List of bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 70));

        // Display bogies
        System.out.println("Bogies in Train:");
        bogies.forEach(System.out::println);

        // --- AGGREGATE USING REDUCE ----
        int totalCapacity = bogies.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity of Train: " + totalCapacity);
        System.out.println("\nUC10 aggregation completed...");
    }
}
