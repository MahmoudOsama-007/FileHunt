package Strategy;

import java.io.File;
import java.util.List;

public class SeqNameFileSearch implements SearchStrategy {
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
                searchDirectory(file,query);  // Recurse into subdirectories
            } else {
                if (filenameMatches(file,query)) {
                   // System.out.println(file.getAbsolutePath()); // Test
                    matchedFiles.add(file);
                }
            }
        }
    }
    private boolean filenameMatches(File file,String query) {
        //Case Sensitive
        return file.getName().contains(query);
    }
}
