import Strategy.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileSearchContext context = new FileSearchContext();
        File[] drives = File.listRoots(); //list of Available Drive
        ArrayList<String> letters = new ArrayList<>();

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
            //list Drive
            for (File drive : drives) {
                String letter = drive.getPath().substring(0, 1);
                letters.add(letter);
            }
            // Join using OR
            String result = String.join(" OR ", letters);
            System.out.println(result);
            System.out.print("Enter Drive to search: ");
            String path = scanner.nextLine().toUpperCase()+":\\"; // need to Validation if enter wrong Drive
            switch (choice) {
                case 1:
                    context.setStrategy(new SequentialWordSearchStrategy());
                    break;
                case 2:
                    context.setStrategy(new ParallelWordSearchStrategy());
                    break;
                case 3:
                    context.setStrategy(new SequentialNameSearchStrategy());
                    break;
                case 4:
                    context.setStrategy(new ParallelNameSearchStrategy());
                    break;
                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            List<File> results = context.search(query, path);


            if (results.isEmpty()) {
                System.out.println("No files found.");
            } else {
                System.out.println("Found files:");
                for (File file : results) {
                    System.out.println(file.getAbsolutePath());
                }
            }

        }

        scanner.close();
    }
}
