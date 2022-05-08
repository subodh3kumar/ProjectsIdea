package workshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import workshop.service.CsvService;

@Slf4j
@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    private final CsvService service;

    public MainApplication(CsvService service) {
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
            log.info("ERROR:", e);
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
