/**
 * ================================================================
 * MAIN CLASS - UseCase15TrainConsistMgmt
 * ================================================================
 *
 * Use Case 15: Safe Cargo Assignment Using try-catch-finally
 *
 * Description:
 * This class safely assigns cargo to goods bogies
 * while handling unsafe combinations using structured
 * exception handling blocks.
 *
 * @author Akshat
 * @version 15.0
 */
public class UseCase15TrainConsistMgmt {

    // ---- CUSTOM RUNTIME EXCEPTION ----
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
            super(message);
        }
    }

    // Goods Bogie model
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type) {
            this.type = type;
        }

        public void assignCargo(String cargo) {
            try {
                if (type.equals("Rectangular") && cargo.equals("Petroleum")) {
                    throw new CargoSafetyException("Unsafe assignment: Petroleum cannot be carried in Rectangular bogie");
                }
                this.cargo = cargo;
                System.out.println("Cargo assigned successfully: " + type + " -> " + cargo);
            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Cargo assignment validation completed for bogie type: " + type);
            }
        }

        @Override
        public String toString() {
            return type + " -> " + (cargo == null ? "No Cargo" : cargo);
        }
    }

    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println(" UC15 - Safe Cargo Assignment Using try-catch-finally ");
        System.out.println("===============================================\n");

        GoodsBogie b1 = new GoodsBogie("Cylindrical");
        b1.assignCargo("Petroleum"); // ✅ safe

        GoodsBogie b2 = new GoodsBogie("Rectangular");
        b2.assignCargo("Petroleum"); // ❌ unsafe, handled gracefully

        GoodsBogie b3 = new GoodsBogie("Box");
        b3.assignCargo("Grain"); // ✅ safe

        System.out.println("\nUC15 cargo assignment completed...");
    }
}
