import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class UseCase17TrainConsistMgmtTest {

    @Test
    public void testSort_BasicAlphabeticalSorting() {
        String[] bogies = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};
        Arrays.sort(bogies);
        assertArrayEquals(new String[]{"AC Chair", "First Class", "General", "Luxury", "Sleeper"}, bogies);
    }

    @Test
    public void testSort_UnsortedInput() {
        String[] bogies = {"Luxury", "General", "Sleeper", "AC Chair"};
        Arrays.sort(bogies);
        assertArrayEquals(new String[]{"AC Chair", "General", "Luxury", "Sleeper"}, bogies);
    }

    @Test
    public void testSort_AlreadySortedArray() {
        String[] bogies = {"AC Chair", "First Class", "General"};
        Arrays.sort(bogies);
        assertArrayEquals(new String[]{"AC Chair", "First Class", "General"}, bogies);
    }

    @Test
    public void testSort_DuplicateBogieNames() {
        String[] bogies = {"Sleeper", "AC Chair", "Sleeper", "General"};
        Arrays.sort(bogies);
        assertArrayEquals(new String[]{"AC Chair", "General", "Sleeper", "Sleeper"}, bogies);
    }

    @Test
    public void testSort_SingleElementArray() {
        String[] bogies = {"Sleeper"};
        Arrays.sort(bogies);
        assertArrayEquals(new String[]{"Sleeper"}, bogies);
    }
}
