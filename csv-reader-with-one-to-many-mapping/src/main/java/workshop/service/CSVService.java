package workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.component.CSVReader;
import workshop.repository.CSVRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class CSVService {
    private static final String LOCATION = "src/main/resources/csv";
    private final CSVReader reader;
    //private final CSVRepository repository;

    @Autowired
    public CSVService(CSVReader reader) {
        this.reader = reader;
        //this.repository = repository;
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
            List<Object> parse = reader.parse(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
