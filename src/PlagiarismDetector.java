import java.util.*;

class PlagiarismDetector {

    private Map<String, Set<Integer>> index = new HashMap<>();

    public List<String> generateNgrams(String text, int n) {

        String[] words = text.split(" ");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {

            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < n; j++) {
                sb.append(words[i + j]).append(" ");
            }

            grams.add(sb.toString().trim());
        }

        return grams;
    }

    public void addDocument(int docId, String text) {

        List<String> ngrams = generateNgrams(text, 5);

        for (String gram : ngrams) {

            index.putIfAbsent(gram, new HashSet<>());
            index.get(gram).add(docId);
        }
    }

    public Map<Integer, Integer> analyzeDocument(String text) {

        List<String> ngrams = generateNgrams(text, 5);

        Map<Integer, Integer> matches = new HashMap<>();

        for (String gram : ngrams) {

            if (index.containsKey(gram)) {

                for (int doc : index.get(gram)) {

                    matches.put(doc,
                            matches.getOrDefault(doc, 0) + 1);
                }
            }
        }

        return matches;
    }
}