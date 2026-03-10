import java.util.*;

class git checkout develop {

    Map<String, Integer> frequency = new HashMap<>();

    public void addQuery(String query) {

        frequency.put(query,
                frequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        PriorityQueue<String> pq =
                new PriorityQueue<>(
                        (a, b) -> frequency.get(b) - frequency.get(a));

        for (String query : frequency.keySet()) {

            if (query.startsWith(prefix)) {
                pq.add(query);
            }
        }

        List<String> result = new ArrayList<>();

        int k = 10;

        while (!pq.isEmpty() && k-- > 0) {
            result.add(pq.poll());
        }

        return result;
    }
}