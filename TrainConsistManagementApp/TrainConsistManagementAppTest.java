import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class UseCase12TrainConsistMgmtTest {

    static class GoodsBogie {
        String type;
        String cargo;
        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    private boolean validateSafety(List<GoodsBogie> bogies) {
        return bogies.stream()
                .allMatch(b -> !(b.type.equals("Cylindrical") && !b.cargo.equals("Petroleum")));
    }

    @Test
    public void testSafety_AllBogiesValid() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Open", "Coal")
        );
        assertTrue(validateSafety(bogies));
    }

    @Test
    public void testSafety_CylindricalWithInvalidCargo() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Coal")
        );
        assertFalse(validateSafety(bogies));
    }

    @Test
    public void testSafety_NonCylindricalBogiesAllowed() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Box", "Grain"),
                new GoodsBogie("Open", "Coal")
        );
        assertTrue(validateSafety(bogies));
    }

    @Test
    public void testSafety_MixedBogiesWithViolation() {
        List<GoodsBogie> bogies = Arrays.asList(
                new GoodsBogie("Cylindrical", "Petroleum"),
                new GoodsBogie("Cylindrical", "Coal")
        );
        assertFalse(validateSafety(bogies));
    }

    @Test
    public void testSafety_EmptyBogieList() {
        List<GoodsBogie> bogies = new ArrayList<>();
        assertTrue(validateSafety(bogies));
    }
}
