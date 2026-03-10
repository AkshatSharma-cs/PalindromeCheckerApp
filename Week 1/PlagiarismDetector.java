// PROBLEM 4

import java.util.*;

public class PlagiarismDetector {
    private final Map<String, Set<String>> nGramIndex; // n-gram -> set of document IDs
    private final int n; // size of n-gram (e.g., 5 words)

    public PlagiarismDetector(int n) {
        this.n = n;
        this.nGramIndex = new HashMap<>();
    }

    // Break text into n-grams
    private List<String> extractNGrams(String text) {
        String[] words = text.split("\\s+");
        List<String> nGrams = new ArrayList<>();
        for (int i = 0; i <= words.length - n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(words[i + j]).append(" ");
            }
            nGrams.add(sb.toString().trim());
        }
        return nGrams;
    }

    // Index a document
    public void indexDocument(String docId, String text) {
        List<String> nGrams = extractNGrams(text);
        for (String nGram : nGrams) {
            nGramIndex.computeIfAbsent(nGram, k -> new HashSet<>()).add(docId);
        }
    }

    // Analyze similarity between a new document and indexed ones
    public Map<String, Double> analyzeDocument(String docId, String text) {
        List<String> nGrams = extractNGrams(text);
        Map<String, Integer> matchCounts = new HashMap<>();

        for (String nGram : nGrams) {
            if (nGramIndex.containsKey(nGram)) {
                for (String existingDoc : nGramIndex.get(nGram)) {
                    matchCounts.put(existingDoc, matchCounts.getOrDefault(existingDoc, 0) + 1);
                }
            }
        }

        Map<String, Double> similarityScores = new HashMap<>();
        for (Map.Entry<String, Integer> entry : matchCounts.entrySet()) {
            double similarity = (entry.getValue() * 100.0) / nGrams.size();
            similarityScores.put(entry.getKey(), similarity);
        }

        return similarityScores;
    }

    // Demo
    public static void main(String[] args) {
        PlagiarismDetector detector = new PlagiarismDetector(5);

        // Index existing documents
        detector.indexDocument("essay_089", "This is a sample essay with some unique content for testing plagiarism detection.");
        detector.indexDocument("essay_092", "This essay contains a lot of similar words and repeated content for plagiarism testing.");

        // Analyze new document
        Map<String, Double> results = detector.analyzeDocument("essay_123",
                "This essay contains a lot of similar words and repeated content for plagiarism testing.");

        for (Map.Entry<String, Double> entry : results.entrySet()) {
            System.out.println("Similarity with " + entry.getKey() + ": " + entry.getValue() + "%");
        }
    }
}
