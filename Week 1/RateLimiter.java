//Problem 6

import java.util.*;
import java.util.concurrent.*;

class UserBucket {
    private final int capacity;      // max requests allowed
    private final long refillInterval; // in milliseconds
    private int tokens;
    private long lastRefill;

    public UserBucket(int capacity, int refillPerSecond) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.refillInterval = 1000L / refillPerSecond;
        this.lastRefill = System.currentTimeMillis();
    }

    // Try to consume a token
    public synchronized boolean allowRequest() {
        refillTokens();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }

    // Refill tokens based on elapsed time
    private void refillTokens() {
        long now = System.currentTimeMillis();
        long elapsed = now - lastRefill;
        int refillCount = (int)(elapsed / refillInterval);
        if (refillCount > 0) {
            tokens = Math.min(capacity, tokens + refillCount);
            lastRefill = now;
        }
    }
}

public class RateLimiter {
    private final Map<String, UserBucket> userBuckets;

    public RateLimiter() {
        userBuckets = new ConcurrentHashMap<>();
    }

    // Configure rate limit for a user
    public void configureUser(String userId, int capacity, int refillPerSecond) {
        userBuckets.put(userId, new UserBucket(capacity, refillPerSecond));
    }

    // Check if request is allowed
    public boolean isAllowed(String userId) {
        UserBucket bucket = userBuckets.get(userId);
        if (bucket == null) {
            throw new IllegalArgumentException("User not configured: " + userId);
        }
        return bucket.allowRequest();
    }

    // Demo
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter();
        limiter.configureUser("user_123", 5, 2); // 5 requests max, 2 tokens/sec refill

        for (int i = 0; i < 10; i++) {
            boolean allowed = limiter.isAllowed("user_123");
            System.out.println("Request " + (i+1) + ": " + (allowed ? "ALLOWED" : "BLOCKED"));
            Thread.sleep(300); // simulate request interval
        }
    }
}
