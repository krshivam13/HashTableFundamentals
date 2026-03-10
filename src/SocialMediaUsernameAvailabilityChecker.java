import java.util.*;

class UsernameChecker {

    private Map<String, Integer> usernameToUserId = new HashMap<>();
    private Map<String, Integer> attemptFrequency = new HashMap<>();

    public boolean checkAvailability(String username) {

        attemptFrequency.put(username,
                attemptFrequency.getOrDefault(username, 0) + 1);

        return !usernameToUserId.containsKey(username);
    }

    public void register(String username, int userId) {
        usernameToUserId.put(username, userId);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String candidate = username + i;
            if (!usernameToUserId.containsKey(candidate)) {
                suggestions.add(candidate);
            }
        }

        String alt = username.replace("_", ".");
        if (!usernameToUserId.containsKey(alt)) {
            suggestions.add(alt);
        }

        return suggestions;
    }

    public String getMostAttempted() {

        String result = "";
        int max = 0;

        for (String user : attemptFrequency.keySet()) {
            if (attemptFrequency.get(user) > max) {
                max = attemptFrequency.get(user);
                result = user;
            }
        }

        return result;
    }
}