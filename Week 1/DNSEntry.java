// PROBLEM 3
import java.util.*;

class DNSEntry {
    String domain;
    String ipAddress;
    long expiryTime;

    DNSEntry(String domain, String ipAddress, int ttlSeconds) {
        this.domain = domain;
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttlSeconds * 1000;
    }

    boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCache {
    private final Map<String, DNSEntry> cache;
    private int hits;
    private int misses;

    public DNSCache() {
        cache = new HashMap<>();
        hits = 0;
        misses = 0;
    }

    // Resolve domain with TTL check
    public String resolve(String domain) {
        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);
            if (!entry.isExpired()) {
                hits++;
                return "Cache HIT → " + entry.ipAddress;
            } else {
                cache.remove(domain);
                return queryUpstream(domain);
            }
        } else {
            return queryUpstream(domain);
        }
    }

    // Simulate upstream DNS query
    private String queryUpstream(String domain) {
        misses++;
        // Fake IP for demo purposes
        String ip = "172.217." + new Random().nextInt(255) + "." + new Random().nextInt(255);
        cache.put(domain, new DNSEntry(domain, ip, 5)); // TTL = 5s for demo
        return "Cache MISS → Queried upstream → " + ip;
    }

    // Cache statistics
    public String getCacheStats() {
        int total = hits + misses;
        double hitRate = total == 0 ? 0 : (hits * 100.0 / total);
        return "Hit Rate: " + hitRate + "%, Hits: " + hits + ", Misses: " + misses;
    }

    // Background cleanup (optional)
    public void cleanupExpired() {
        cache.entrySet().removeIf(e -> e.getValue().isExpired());
    }

    // Demo
    public static void main(String[] args) throws InterruptedException {
        DNSCache dnsCache = new DNSCache();

        System.out.println(dnsCache.resolve("google.com")); // MISS
        System.out.println(dnsCache.resolve("google.com")); // HIT
        Thread.sleep(6000); // wait for TTL expiry
        System.out.println(dnsCache.resolve("google.com")); // EXPIRED → MISS
        System.out.println(dnsCache.getCacheStats());
    }
}
