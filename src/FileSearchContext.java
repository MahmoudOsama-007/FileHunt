import Strategy.SearchStrategy;

import java.io.File;
import java.util.List;

public class FileSearchContext {
    private SearchStrategy strategy;

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }
    public List<File> search(String query, String path) {
        return strategy.search(query, path);
    }

}
