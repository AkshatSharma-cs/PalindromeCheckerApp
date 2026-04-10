package PACKAGE_NAME.TrainConsistManagementApp;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrainConsistManagementAppTest {   // <-- make class public

    // Reuse Bogie model from UC8
    static class Bogie {
        String name;
        int capacity;
        Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }

    @Test
    public void testFilter_CapacityGreaterThanThreshold() {   // <-- make method public
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("AC Chair", 56),
                new Bogie("First Class", 24)
        );

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertEquals(1, filtered.size());
        assertEquals("Sleeper", filtered.get(0).name);
    }

    @Test
    public void testFilter_NoBogiesMatching() {   // <-- make method public
        List<Bogie> bogies = List.of(
                new Bogie("First Class", 24),
                new Bogie("AC Chair", 56)
        );

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 100)
                .collect(Collectors.toList());

        assertTrue(filtered.isEmpty());
    }

    @Test
    public void testFilter_MultipleBogiesMatching() {   // <-- make method public
        List<Bogie> bogies = List.of(
                new Bogie("Sleeper", 72),
                new Bogie("General", 90),
                new Bogie("AC Chair", 56)
        );

        List<Bogie> filtered = bogies.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        assertEquals(2, filtered.size());
        assertTrue(filtered.stream().anyMatch(b -> b.name.equals("Sleeper")));
        assertTrue(filtered.stream().anyMatch(b -> b.name.equals("General")));
    }
}
