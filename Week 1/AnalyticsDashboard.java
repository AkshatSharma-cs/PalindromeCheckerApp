// PROBLEM 5

import java.util.*;

public class AnalyticsDashboard {
    private final Map<String, Integer> pageViews;          // pageUrl -> visitCount
    private final Map<String, Set<String>> uniqueVisitors; // pageUrl -> set of userIds
    private final Map<String, Integer> trafficSources;     // source -> count

    public AnalyticsDashboard() {
        pageViews = new HashMap<>();
        uniqueVisitors = new HashMap<>();
        trafficSources = new HashMap<>();
    }

    // Process incoming page view event
    public void processEvent(String url, String userId, String source) {
        // Count page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // Track unique visitors
        uniqueVisitors.computeIfAbsent(url, k -> new HashSet<>()).add(userId);

        // Count traffic sources
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    // Get top N pages by views
    public List<String> getTopPages(int n) {
        return pageViews.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(n)
                .map(e -> e.getKey() + " - " + e.getValue() + " views (" +
                        uniqueVisitors.getOrDefault(e.getKey(), new HashSet<>()).size() + " unique)")
                .toList();
    }

    // Get traffic source distribution
    public Map<String, String> getTrafficSources() {
        int total = trafficSources.values().stream().mapToInt(Integer::intValue).sum();
        Map<String, String> distribution = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : trafficSources.entrySet()) {
            double percent = total == 0 ? 0 : (entry.getValue() * 100.0 / total);
            distribution.put(entry.getKey(), String.format("%.1f%%", percent));
        }
        return distribution;
    }

    // Demo
    public static void main(String[] args) {
        AnalyticsDashboard dashboard = new AnalyticsDashboard();

        // Simulate events
        dashboard.processEvent("/article/breaking-news", "user_123", "google");
        dashboard.processEvent("/article/breaking-news", "user_456", "facebook");
        dashboard.processEvent("/sports/championship", "user_789", "direct");
        dashboard.processEvent("/article/breaking-news", "user_123", "google"); // repeat user

        // Show dashboard
        System.out.println("Top Pages:");
        dashboard.getTopPages(10).forEach(System.out::println);

        System.out.println("\nTraffic Sources:");
        dashboard.getTrafficSources().forEach((src, pct) ->
                System.out.println(src + ": " + pct));
    }
}
