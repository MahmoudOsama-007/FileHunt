package Strategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface SearchStrategy {
    List<File> matchedFiles = new ArrayList<>();
    public List<File> search(String query, String path);
}
