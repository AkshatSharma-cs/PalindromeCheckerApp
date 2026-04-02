import java.util.HashSet;
import java.util.Set;

/**
 * ================================================
 * MAIN CLASS - TrainConsistManagementApp
 * ================================================
 *
 * Use Case 3: Track Unique Bogie IDs
 *
 * Description:
 * This class ensures that duplicate bogie IDs are not
 * added into the train formation using HashSet.
 *
 * At this stage, the application:
 * - Stores bogie IDs
 * - Prevents duplicates automatically
 * - Displays unique bogie identifiers
 *
 * This maps uniqueness validation using Set.
 *
 * @author Akshat
 * @version 3.0
 */
public class TrainConsistManagementApp {
    public static void main(String[] args) {

        System.out.println("===========================================");
        System.out.println(" UC3 - Track Unique Bogie IDs ");
        System.out.println("===========================================\n");

        // Create a Set to store unique bogie IDs
        Set<String> bogies = new HashSet<>();

        // ---- ADD IDs (including duplicates) ----
        bogies.add("BG101");
        bogies.add("BG102");
        bogies.add("BG103");
        bogies.add("BG104");

        // Duplicate entries will be ignored internally by HashSet
        bogies.add("BG101"); // Duplicate entry
        bogies.add("BG102"); // Duplicate entry

        // Display bogie IDs after insertion
        System.out.println("Bogie IDs After Insertion:");
        System.out.println(bogies);

        System.out.println("\nNote:");
        System.out.println("Duplicates are automatically ignored by HashSet.");

        System.out.println("\nUC3 uniqueness validation completed...");
    }
}
