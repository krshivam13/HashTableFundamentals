import java.util.*;

class RateLimiter {

    class TokenBucket {

        int tokens;
        long lastRefill;

        TokenBucket(int tokens) {
            this.tokens = tokens;
            this.lastRefill = System.currentTimeMillis();
        }
    }

    Map<String, TokenBucket> clients = new HashMap<>();

    int MAX_TOKENS = 1000;

    public boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId,
                new TokenBucket(MAX_TOKENS));

        TokenBucket bucket = clients.get(clientId);

        refill(bucket);

        if (bucket.tokens > 0) {

            bucket.tokens--;
            return true;
        }

        return false;
    }

    private void refill(TokenBucket bucket) {

        long now = System.currentTimeMillis();

        if (now - bucket.lastRefill > 3600000) {

            bucket.tokens = MAX_TOKENS;
            bucket.lastRefill = now;
        }
    }
}