package workshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import workshop.model.Name;
import workshop.repository.NameRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NameService {

    private final NameRepository nameRepository;

    public NameService(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public void saveAll(String path) {
        log.info("saveAll() method start");
        removeOldNames();
        List<Name> names = getNames(path);
        saveNewNames(names);
    }

    private void saveNewNames(List<Name> names) {
        log.info("saveNewNames() method start");
        nameRepository.saveAll(names);
    }

    private void removeOldNames() {
        log.info("removeOldNames() method start");
        nameRepository.deleteAll();
    }

    private List<Name> getNames(String path) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            log.error("ERROR", e);
        }
        assert lines != null;
        lines.remove(0);
        return lines.stream().map(this::convert).collect(Collectors.toList());
    }

    private Name convert(String line) {
        String[] words = line.split(",", -1);
        Name name = new Name();
        name.setFirstName(words[0].toUpperCase());
        name.setLastName(words[1].toUpperCase());
        name.setAge(Integer.parseInt(words[2]));
        return name;
    }
}
