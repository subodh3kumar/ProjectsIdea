package workshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import workshop.service.CSVService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@SpringBootApplication
public class MainApplication implements CommandLineRunner {



    private final CSVService service;

    public MainApplication(CSVService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            service.processCsvFiles();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
