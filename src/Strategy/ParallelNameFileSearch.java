package Strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ParallelNameFileSearch implements SearchStrategy {
    public List<CompletableFuture<Path>> searchAsync(String query, String path) {
            try (Stream<Path> pathStream = Files.walk(Paths.get(path))) {
                return pathStream
                        // Launch a CompletableFuture for each path
                        .map(p -> CompletableFuture.supplyAsync(() -> {
                            if (Files.isRegularFile(p) && filenameMatches(p, query)) {
                                return p;
                            }
                            return null;
                        }))
                        .toList();
            }
        catch (IOException e) {
            throw new RuntimeException("Failed to walk file tree", e);
        }
    }
    @Override
    public List<Path> search(String query, String path) {
        return searchAsync(query,path).stream().map(CompletableFuture::join).filter(Objects::nonNull).toList();
    }
    private boolean filenameMatches(Path path,String query) {
        //Case Sensitive
        return path.getFileName().toString().contains(query);
    }
}
