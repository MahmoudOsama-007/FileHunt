package Strategy;

import java.io.File;
import java.util.List;

public class SequentialNameSearchStrategy implements SearchStrategy {
    @Override
    public List<File> search(String query, String path) {
        System.out.println("Dummy Search");
        return List.of();
    }
}
