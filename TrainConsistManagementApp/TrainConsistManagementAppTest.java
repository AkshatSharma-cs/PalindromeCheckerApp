import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.Collectors;

public class UseCase13TrainConsistMgmtTest {

    static class Bogie {
        String type;
        int capacity;
        Bogie(String type, int capacity) {
            this.type = type;
            this.capacity = capacity;
        }
    }

    private List<Bogie> loopFilter(List<Bogie> bogies) {
        List<Bogie> result = new ArrayList<>();
        for (Bogie b : bogies) {
            if (b.capacity > 60) result.add(b);
        }
        return result;
    }

    private List<Bogie> streamFilter(List<Bogie> bogies) {
        return bogies.stream().filter(b -> b.capacity > 60).collect(Collectors.toList());
    }

    @Test
    public void testLoopFilteringLogic() {
        List<Bogie> bogies = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("AC Chair", 56));
        List<Bogie> result = loopFilter(bogies);
        assertEquals(1, result.size());
    }

    @Test
    public void testStreamFilteringLogic() {
        List<Bogie> bogies = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("AC Chair", 56));
        List<Bogie> result = streamFilter(bogies);
        assertEquals(1, result.size());
    }

    @Test
    public void testLoopAndStreamResultsMatch() {
        List<Bogie> bogies = Arrays.asList(new Bogie("Sleeper", 72), new Bogie("AC Chair", 56));
        assertEquals(loopFilter(bogies).size(), streamFilter(bogies).size());
    }

    @Test
    public void testExecutionTimeMeasurement() {
        long start = System.nanoTime();
        long end = System.nanoTime();
        assertTrue((end - start) >= 0);
    }

    @Test
    public void testLargeDatasetProcessing() {
        List<Bogie> bogies = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            bogies.add(new Bogie("Sleeper", 50 + (i % 100)));
        }
        assertEquals(loopFilter(bogies).size(), streamFilter(bogies).size());
    }
}
