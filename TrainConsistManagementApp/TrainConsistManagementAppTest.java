import org.junit.Test;
import static org.junit.Assert.*;

public class UseCase15TrainConsistMgmtTest {

    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) { super(message); }
    }

    static class GoodsBogie {
        String type;
        String cargo;
        GoodsBogie(String type) { this.type = type; }
        void assignCargo(String cargo) {
            if (type.equals("Rectangular") && cargo.equals("Petroleum")) {
                throw new CargoSafetyException("Unsafe assignment: Petroleum cannot be carried in Rectangular bogie");
            }
            this.cargo = cargo;
        }
    }

    @Test
    public void testCargo_SafeAssignment() {
        GoodsBogie b = new GoodsBogie("Cylindrical");
        b.assignCargo("Petroleum");
        assertEquals("Petroleum", b.cargo);
    }

    @Test(expected = CargoSafetyException.class)
    public void testCargo_UnsafeAssignmentHandled() {
        GoodsBogie b = new GoodsBogie("Rectangular");
        b.assignCargo("Petroleum");
    }

    @Test
    public void testCargo_CargoNotAssignedAfterFailure() {
        GoodsBogie b = new GoodsBogie("Rectangular");
        try {
            b.assignCargo("Petroleum");
        } catch (CargoSafetyException e) {
            assertNull(b.cargo);
        }
    }

    @Test
    public void testCargo_ProgramContinuesAfterException() {
        GoodsBogie b1 = new GoodsBogie("Rectangular");
        try { b1.assignCargo("Petroleum"); } catch (CargoSafetyException e) {}
        GoodsBogie b2 = new GoodsBogie("Box");
        b2.assignCargo("Grain");
        assertEquals("Grain", b2.cargo);
    }

    @Test
    public void testCargo_FinallyBlockExecution() {
        try {
            GoodsBogie b = new GoodsBogie("Rectangular");
            b.assignCargo("Petroleum");
        } catch (CargoSafetyException e) {
            assertEquals("Unsafe assignment: Petroleum cannot be carried in Rectangular bogie", e.getMessage());
        }
    }
}
