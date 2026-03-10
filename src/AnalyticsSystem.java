import java.util.*;

class AnalyticsSystem {

    Map<String, Integer> pageViews = new HashMap<>();
    Map<String, Set<String>> uniqueVisitors = new HashMap<>();
    Map<String, Integer> sourceCount = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url,
                pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        sourceCount.put(source,
                sourceCount.getOrDefault(source, 0) + 1);
    }

    public void printDashboard() {

        System.out.println("Top Pages:");

        for (String page : pageViews.keySet()) {

            System.out.println(
                    page + " - "
                            + pageViews.get(page)
                            + " views (" +
                            uniqueVisitors.get(page).size()
                            + " unique)");
        }

        System.out.println("\nTraffic Sources:");

        for (String source : sourceCount.keySet()) {

            System.out.println(
                    source + " : "
                            + sourceCount.get(source));
        }
    }
}