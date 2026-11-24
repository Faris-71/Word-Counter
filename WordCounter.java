
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter text file path: ");
        String filePath = input.nextLine().trim();

        // Feature 1: Input Validation
        if (filePath.isEmpty()) {
            System.out.println("Error: File path cannot be empty.");
            input.close();
            return;
        }

        if (!filePath.toLowerCase(Locale.ROOT).endsWith(".txt")) {
            System.out.println("Error: Only .txt files are allowed.");
            input.close();
            return;
        }

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Error: File not found.");
            input.close();
            return;
        }

        if (!file.canRead()) {
            System.out.println("Error: File cannot be read.");
            input.close();
            return;
        }

        try (Scanner fileReader = new Scanner(file)) {

            int wordCount = 0;
            int lineCount = 0;
            int charCount = 0;

            // Feature 3: Word Frequency
            Map<String, Integer> frequency = new HashMap<>();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                // Feature 2: Lines and Characters
                lineCount++;
                charCount += line.length();

                String trimmed = line.trim();

                if (!trimmed.isEmpty()) {
                    String[] words = trimmed.split("\\s+");

                    for (String originalWord : words) {
                        wordCount++;

                        String word = originalWord
                                .toLowerCase(Locale.ROOT)
                                .replaceAll("[^a-z0-9]", "");

                        if (!word.isEmpty()) {
                            frequency.put(
                                word,
                                frequency.getOrDefault(word, 0) + 1
                            );
                        }
                    }
                }
            }

            // Find the most common word
            String mostCommon = "";
            int highestCount = 0;

            for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
                if (entry.getValue() > highestCount ||
                    (entry.getValue() == highestCount &&
                     entry.getKey().compareTo(mostCommon) < 0)) {

                    mostCommon = entry.getKey();
                    highestCount = entry.getValue();
                }
            }

            // Display results
            System.out.println("Total words: " + wordCount);
            System.out.println("Total lines: " + lineCount);
            System.out.println("Total characters: " + charCount);

            if (highestCount > 0) {
                System.out.println("Most common word: " + mostCommon);
                System.out.println("Frequency: " + highestCount);
            } else {
                System.out.println("No words found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file.");
        }

        input.close();
    }
}
