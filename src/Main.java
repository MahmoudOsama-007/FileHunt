import Strategy.*;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileSearchContext context = new FileSearchContext();

        while (true) {
            System.out.println("Choose a search strategy:");
            System.out.println("1. Find files containing a word (Sequential)");
            System.out.println("2. Find files containing a word (Parallel)");
            System.out.println("3. Find file by name (Sequential)");
            System.out.println("4. Find file by name (Parallel)");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 5) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter search query (word or filename): ");
            String query = scanner.nextLine();


            System.out.print("Enter Drive to search: ");
            String path = scanner.nextLine(); // need to Validation if enter wrong Drive
            switch (choice) {
                case 1:
                    context.setStrategy(new SeqStreamContentFileSearch());
                    break;
                case 2:
                    context.setStrategy(new ParallelContentFileSearch());
                    break;
                case 3:
                    context.setStrategy(new SeqStreamNameFileSearch());
                    break;
                case 4:
                    context.setStrategy(new ParallelNameFileSearch());
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            List<Path> results = context.search(query, path);


            if (results.isEmpty()) {
                System.out.println("No files found.");
            } else {
                System.out.println("Found files:");
                for (Path p : results) {
                    System.out.println(p.toString());
                }
            }

        }

        scanner.close();
    }
}
