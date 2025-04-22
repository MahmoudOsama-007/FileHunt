package Strategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SequentialNameSearchStrategy implements SearchStrategy {
    @Override
    public List<File> search(String query, String path) {
        List<File> matchedFiles = new ArrayList<>();
        File root = new File(path);

        if (root.exists() && root.isDirectory()) {
            searchDirectory(root,query ,matchedFiles);
        } else {
            System.err.println("Invalid path: " + path);
        }

        return matchedFiles;
    }

    private void searchDirectory(File dir,String query,List<File> matchedFiles) {
        File[] files = dir.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchDirectory(file,query,matchedFiles);  // Recurse into subdirectories
            } else {
                if (filenameMatches(file,query)) {
                    System.out.println(file.getAbsolutePath()); // Test
                    matchedFiles.add(file);
                }
            }
        }
    }
    private boolean filenameMatches(File file,String query) {
        return file.getName().contains(query);
    }
}
