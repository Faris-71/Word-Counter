
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter text file path: ");
        String filePath = input.nextLine().trim();

        if (filePath.isEmpty()) {
            System.out.println("Error: File path cannot be empty.");
            input.close();
            return;
        }

        if (!filePath.toLowerCase().endsWith(".txt")) {
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

            while (fileReader.hasNext()) {
                fileReader.next();
                wordCount++;
            }

            System.out.println("Total words: " + wordCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file.");
        }

        input.close();
    }
}
