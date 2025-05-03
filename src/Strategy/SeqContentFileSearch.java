package Strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SeqContentFileSearch implements SearchStrategy {

    @Override
    public List<File> search(String query, String path) {
        File root = new File(path);
        if (root.exists() && root.isDirectory()) {
            searchDirectory(root,query);
        } else {
            System.err.println("Invalid path: " + path);
        }

        return matchedFiles;
    }
    private void searchDirectory(File dir,String query) {
        File[] files = dir.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchDirectory(file,query);  // Recursive call
            } else {
                if (containsQueryWord(file,query)) {
                   // System.out.println(file.getAbsolutePath()); // Test
                    matchedFiles.add(file);
                }
            }
        }
    }
    private boolean containsQueryWord(File file, String query) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(query)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to read file: " + file.getAbsolutePath());
        }

        return false;
    }



}
