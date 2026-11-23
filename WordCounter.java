
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

            while (fileReader.hasNext()) {
                fileReader.next();
                wordCount++;
            }

            fileReader.close();

            System.out.println("Total words: " + wordCount);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        input.close();
    }
}
