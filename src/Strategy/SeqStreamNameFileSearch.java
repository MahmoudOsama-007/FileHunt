package Strategy;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class SeqStreamNameFileSearch implements SearchStrategy {

    @Override
    public List<Path> search(String query, Path path) {
        try(Stream<Path> stream = Files.walk(path)) {
            return stream.filter(p->Files.isRegularFile(p)&&filenameMatches(p,query)).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean filenameMatches(Path path,String query) {
        //Case Sensitive
        return path.getFileName().toString().contains(query);
    }
}
