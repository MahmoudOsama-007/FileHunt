package Strategy;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public interface SearchStrategy {
    List<Path> matchedFiles = new ArrayList<>();
    public List<Path> search(String query, Path path);
}
