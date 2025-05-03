import Strategy.SearchStrategy;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileSearchContext {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }
    public List<Path> search(String query, String dir) {
        Path path = Paths.get(dir);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Path does not exist: " + dir);
        }

        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path is not a directory: " + dir);
        }

        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("Path is not readable: " + dir);
        }
        return strategy.search(query, path);
    }

}
