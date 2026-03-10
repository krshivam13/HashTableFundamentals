import java.util.*;

class MultiLevelCache {

    LinkedHashMap<String, String> L1;
    Map<String, String> L2;
    Map<String, String> L3;

    int L1_CAPACITY = 3;

    MultiLevelCache() {

        L1 = new LinkedHashMap<>(L1_CAPACITY, 0.75f, true) {

            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > L1_CAPACITY;
            }
        };

        L2 = new HashMap<>();
        L3 = new HashMap<>();
    }

    public String getVideo(String id) {

        if (L1.containsKey(id)) {

            System.out.println("L1 HIT");
            return L1.get(id);
        }

        if (L2.containsKey(id)) {

            System.out.println("L2 HIT");

            String data = L2.get(id);

            L1.put(id, data);

            return data;
        }

        System.out.println("L3 HIT");

        String data = L3.get(id);

        L2.put(id, data);

        return data;
    }

    public void addVideo(String id, String data) {
        L3.put(id, data);
    }
}