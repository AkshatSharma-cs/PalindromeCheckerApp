import org.junit.Test;
import static org.junit.Assert.*;

public class UseCase18TrainConsistMgmtTest {

    private boolean linearSearch(String[] arr, String key) {
        for (String id : arr) {
            if (id.equals(key)) return true;
        }
        return false;
    }

    @Test
    public void testSearch_BogieFound() {
        String[] bogies = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(linearSearch(bogies, "BG309"));
    }

    @Test
    public void testSearch_BogieNotFound() {
        String[] bogies = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertFalse(linearSearch(bogies, "BG999"));
    }

    @Test
    public void testSearch_FirstElementMatch() {
        String[] bogies = {"BG101", "BG205", "BG309"};
        assertTrue(linearSearch(bogies, "BG101"));
    }

    @Test
    public void testSearch_LastElementMatch() {
        String[] bogies = {"BG101", "BG205", "BG309"};
        assertTrue(linearSearch(bogies, "BG309"));
    }

    @Test
    public void testSearch_SingleElementArray() {
        String[] bogies = {"BG101"};
        assertTrue(linearSearch(bogies, "BG101"));
    }
}
