/**
 * ================================================================
 * MAIN CLASS - UseCase16TrainConsistMgmt
 * ================================================================
 *
 * Use Case 16: Sort Passenger Bogies by Capacity (Bubble Sort)
 *
 * Description:
 * This class demonstrates manual sorting of passenger
 * bogie capacities using the Bubble Sort algorithm
 * instead of built-in sorting utilities.
 *
 * @author Akshat
 * @version 16.0
 */
public class UseCase16TrainConsistMgmt {

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println(" UC16 - Manual Sorting using Bubble Sort ");
        System.out.println("===============================================\n");

        // Create array of passenger bogie capacities
        int[] capacities = {72, 56, 24, 70, 60};

        // Display original order
        System.out.println("Original Capacities:");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        // ---- BUBBLE SORT LOGIC ----
        for (int i = 0; i < capacities.length - 1; i++) {
            for (int j = 0; j < capacities.length - i - 1; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    // Swap
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        // Display sorted result
        System.out.println("\n\nSorted Capacities (Ascending):");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        System.out.println("\n\nUC16 sorting completed...");
    }
}
