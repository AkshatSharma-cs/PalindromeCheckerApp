/**
 * ================================
 * MAIN CLASS - UseCase20TrainConsistMgmt
 * ================================
 *
 * Use Case 20: Exception Handling During Search Operations
 *
 * Description:
 * This class prevents searching when no bogies exist
 * by applying fail-fast validation using exceptions.
 *
 * @author Akshat
 * @version 20.0
 */
public class UseCase20TrainConsistMgmt {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println(" UC20 - Exception Handling During Search ");
        System.out.println("========================================\n");

        // Create bogie array (empty train scenario)
        String[] bogieIds = {};  // no bogies present

        // Search key
        String searchId = "BG101";

        // ----- FAIL-FAST VALIDATION -----
        if (bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available in train. Cannot perform search.");
        }

        // ----- SEARCH LOGIC (executes only if data exists) -----
        boolean found = false;
        for (String id : bogieIds) {
            if (id.equals(searchId)) {
                found = true;
                break;
            }
        }

        // Display result
        if (found) {
            System.out.println("Bogie " + searchId + " found in train consist.");
        } else {
            System.out.println("Bogie " + searchId + " not found in train consist.");
        }

        System.out.println("\nUC20 execution completed...");
    }
}
