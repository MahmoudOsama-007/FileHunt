package Strategy;

import java.io.File;
import java.util.List;

public class SeqStreamNameFileSearch implements SearchStrategy {

    @Override
    public List<File> search(String query, String path) {
        return List.of();
    }
}
