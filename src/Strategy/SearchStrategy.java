package Strategy;

import java.io.File;
import java.util.List;

public interface SearchStrategy {
    public List<File> search(String query, String path);
}
