import Strategy.SearchStrategy;


import java.nio.file.Path;
import java.util.List;

public class FileSearchContext {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }
    public List<Path> search(String query, String path) {
        return strategy.search(query, path);
    }

}
