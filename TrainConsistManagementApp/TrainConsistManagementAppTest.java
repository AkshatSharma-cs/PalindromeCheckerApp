import org.junit.Test;
import static org.junit.Assert.*;

public class UseCase20TrainConsistMgmtTest {

    private boolean searchBogie(String[] bogies, String key) {
        if (bogies.length == 0) {
            throw new IllegalStateException("No bogies available in train. Cannot perform search.");
        }
        for (String id : bogies) {
            if (id.equals(key)) return true;
        }
        return false;
    }

    @Test(expected = IllegalStateException.class)
    public void testSearch_ThrowsExceptionWhenEmpty() {
        String[] bogies = {};
        searchBogie(bogies, "BG101");
    }

    @Test
    public void testSearch_AllowsSearchWhenDataExists() {
        String[] bogies = {"BG101", "BG205"};
        assertTrue(searchBogie(bogies, "BG101"));
    }

    @Test
    public void testSearch_BogieFoundAfterValidation() {
        String[] bogies = {"BG101", "BG205", "BG309"};
        assertTrue(searchBogie(bogies, "BG205"));
    }

    @Test
    public void testSearch_BogieNotFoundAfterValidation() {
        String[] bogies = {"BG101", "BG205", "BG309"};
        assertFalse(searchBogie(bogies, "BG999"));
    }

    @Test
    public void testSearch_SingleElementValidCase() {
        String[] bogies = {"BG101"};
        assertTrue(searchBogie(bogies, "BG101"));
    }
}
