package workshop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import workshop.service.NameService;

@Slf4j
@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    private static final String NAME_PATH = "./src/main/resources/names.txt";

    @Autowired
    public NameService nameService;

    @Override
    public void run(String... args) {
        log.info("run() method start");
        nameService.saveAll(NAME_PATH);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
