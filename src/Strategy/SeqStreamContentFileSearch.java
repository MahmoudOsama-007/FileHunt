package Strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class SeqStreamContentFileSearch implements SearchStrategy{

    @Override
    public List<Path> search(String query, String path) {
        try(Stream<Path> stream = Files.walk(Paths.get(path))) {
            return stream.filter(p->Files.isRegularFile(p)&&containsQueryWord(p,query)).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
