import java.util.*;

class DNSCache {

    class DNSEntry {
        String ip;
        long expiry;

        DNSEntry(String ip, long ttl) {
            this.ip = ip;
            this.expiry = System.currentTimeMillis() + ttl;
        }
    }

    private Map<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        DNSEntry entry = cache.get(domain);

        if (entry != null && entry.expiry > System.currentTimeMillis()) {
            System.out.println("Cache HIT");
            return entry.ip;
        }

        System.out.println("Cache MISS");

        String ip = queryUpstream(domain);

        cache.put(domain, new DNSEntry(ip, 5000));

        return ip;
    }

    private String queryUpstream(String domain) {
        return "172.217.14." + new Random().nextInt(255);
    }
}