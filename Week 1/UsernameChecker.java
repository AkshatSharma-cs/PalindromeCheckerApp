// PROBLEM 1

import java.util.*;

public class UsernameChecker {
    private Map<String, Integer> userMap;          // username -> userId
    private Map<String, Integer> attemptFrequency; // username -> attempts

    public UsernameChecker() {
        userMap = new HashMap<>();
        attemptFrequency = new HashMap<>();
    }

    // Register a username
    public void register(String username, int userId) {
        userMap.put(username, userId);
    }

    // Check availability in O(1)
    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !userMap.containsKey(username);
    }

    // Suggest alternatives if taken
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        if (userMap.containsKey(username)) {
            suggestions.add(username + "1");
            suggestions.add(username + "2");
            suggestions.add(username.replace("_", "."));
        }
        return suggestions;
    }

    // Get most attempted username
    public String getMostAttempted() {
        return attemptFrequency.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");
    }

    // Demo
    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();
        checker.register("john_doe", 1);

        System.out.println(checker.checkAvailability("john_doe"));   // false
        System.out.println(checker.checkAvailability("jane_smith")); // true
        System.out.println(checker.suggestAlternatives("john_doe")); // [john_doe1, john_doe2, john.doe]
        System.out.println("Most attempted: " + checker.getMostAttempted());
    }
}
