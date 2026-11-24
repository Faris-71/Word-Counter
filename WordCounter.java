import java.io.File;
import java.io.FileNotFoundException;
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
            int lineCount = 0;
            int charCount = 0;

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                lineCount++;
                charCount += line.length();

                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    wordCount += trimmed.split("\s+").length;
                }
            }

            fileReader.close();

            System.out.println("Total words: " + wordCount);
            System.out.println("Total lines: " + lineCount);
            System.out.println("Total characters: " + charCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        input.close();
    }
}
