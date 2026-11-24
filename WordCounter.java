import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter text file path: ");
        String filePath = input.nextLine();

        try {
            File file = new File(filePath);
            Scanner fileReader = new Scanner(file);

            int wordCount = 0;
            Map<String, Integer> frequency = new HashMap<>();

            while (fileReader.hasNext()) {
                String word = fileReader.next()
                        .toLowerCase()
                        .replaceAll("^[^\p{L}\p{N}]+|[^\p{L}\p{N}]+$", "");

                if (word.isEmpty()) {
                    continue;
                }

                wordCount++;

                frequency.put(
                    word,
                    frequency.getOrDefault(word, 0) + 1
                );
            }

            fileReader.close();

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

            System.out.println("Total words: " + wordCount);

            if (wordCount > 0) {
                System.out.println("Most common word: " + mostCommon);
                System.out.println("Frequency: " + highestCount);
            } else {
                System.out.println("No words found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        input.close();
    }
}
