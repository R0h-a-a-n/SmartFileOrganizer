import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the input folder path: ");
        String inputFolder = scanner.nextLine().trim();
        String outputFolder = inputFolder + "/organized";
        FileCategorizer.organizeFiles(inputFolder, outputFolder);
        System.out.println("File categorization complete.");
    }
}
