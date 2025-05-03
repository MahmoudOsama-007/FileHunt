package Strategy;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ParallelContentFileSearch implements SearchStrategy {
    public List<CompletableFuture<Path>> searchAsync(String query, Path path){
        try(Stream<Path> pathStream = Files.walk(path)) {
           return pathStream.map((p ->
                CompletableFuture.supplyAsync(()->{
                    if (Files.isRegularFile(p) && containsQueryWord(p, query)) {
                        return p;
                    }
                    return null;
                })
            )).toList();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to walk file tree", e);
        }
    }
    @Override
    public List<Path> search(String query, Path path) {
        return searchAsync(query,path).stream().map(CompletableFuture::join).filter(Objects::nonNull).toList();
    }
    private boolean containsQueryWord(Path path, String query) {

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains(query)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to read file: " + path.getFileName().toString());
        }

        return false;
    }
}
