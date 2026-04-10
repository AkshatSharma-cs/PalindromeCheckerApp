import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class UseCase19TrainConsistMgmtTest {

    private boolean binarySearch(String[] arr, String key) {
        Arrays.sort(arr); // ensure sorted
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = key.compareTo(arr[mid]);
            if (cmp == 0) return true;
            else if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    @Test
    public void testBinarySearch_BogieFound() {
        String[] bogies = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertTrue(binarySearch(bogies, "BG309"));
    }

    @Test
    public void testBinarySearch_BogieNotFound() {
        String[] bogies = {"BG101", "BG205", "BG309", "BG412", "BG550"};
        assertFalse(binarySearch(bogies, "BG999"));
    }

    @Test
    public void testBinarySearch_FirstElementMatch() {
        String[] bogies = {"BG101", "BG205", "BG309"};
        assertTrue(binarySearch(bogies, "BG101"));
    }

    @Test
    public void testBinarySearch_LastElementMatch() {
        String[] bogies = {"BG101", "BG205", "BG309"};
        assertTrue(binarySearch(bogies, "BG309"));
    }

    @Test
    public void testBinarySearch_SingleElementArray() {
        String[] bogies = {"BG101"};
        assertTrue(binarySearch(bogies, "BG101"));
    }

    @Test
    public void testBinarySearch_EmptyArray() {
        String[] bogies = {};
        assertFalse(binarySearch(bogies, "BG101"));
    }

    @Test
    public void testBinarySearch_UnsortedInputHandled() {
        String[] bogies = {"BG309", "BG101", "BG550", "BG205", "BG412"};
        assertTrue(binarySearch(bogies, "BG205"));
    }
}
