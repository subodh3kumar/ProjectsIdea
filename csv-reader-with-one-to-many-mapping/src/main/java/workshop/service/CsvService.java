package workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.component.CsvReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class CsvService {
    private static final String LOCATION = "src/main/resources/csv";
    private final CsvReader reader;

    @Autowired
    public CsvService(CsvReader reader) {
        this.reader = reader;
    }

    public void processCsvFiles() throws Exception {
        log.info("processCsvFiles() method start");

        List<Path> paths = Files.list(Path.of(LOCATION)).toList();
        log.info("{} csv files available in location: {}", paths.size(), LOCATION);

        paths.forEach(path -> log.info(path.getFileName().toString()));

        paths.forEach(this::process);
    }

    private void process(Path path) {
        try {
            reader.parse(path);
        } catch (Exception e) {
            log.info("ERROR:", e);
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
