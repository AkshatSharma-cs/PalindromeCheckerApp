import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * MAIN CLASS - UseCase12TrainConsistMgmt
 * ============================================================
 *
 * Use Case 12: Safety Compliance Check for Goods Bogies
 *
 * Description:
 * This class enforces domain safety rules on goods bogies.
 *
 * At this stage, the application:
 * - Creates goods bogie List
 * - Converts list into stream
 * - Applies safety validation rule
 * - Checks compliance using allMatch()
 * - Displays safety status
 *
 * This maps real-world cargo safety rules using Streams.
 *
 * @author Akshat
 * @version 12.0
 */
public class UseCase12TrainConsistMgmt {

    // Goods Bogie model
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        @Override
        public String toString() {
            return type + " -> " + cargo;
        }
    }

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println(" UC12 - Safety Compliance Check for Goods Bogies ");
        System.out.println("=====================================\n");

        // Create goods bogie list
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Open", "Coal"));
        goodsBogies.add(new GoodsBogie("Box", "Grain"));
        goodsBogies.add(new GoodsBogie("Cylindrical", "Coal")); // ❌ unsafe

        // Display bogies
        System.out.println("Goods Bogies in Train:");
        goodsBogies.forEach(System.out::println);

        // ---- SAFETY VALIDATION ----
        boolean isSafe = goodsBogies.stream()
                .allMatch(b -> !(b.type.equals("Cylindrical") && !b.cargo.equals("Petroleum")));

        System.out.println("\nSafety Compliance Status: " + isSafe);
        if (isSafe) {
            System.out.println("Train formation is SAFE.");
        } else {
            System.out.println("Train formation is NOT SAFE.");
        }

        System.out.println("\nUC12 safety validation completed...");
    }
}
