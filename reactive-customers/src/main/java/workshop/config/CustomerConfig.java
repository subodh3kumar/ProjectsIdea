package workshop.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import workshop.record.Customer;
import workshop.repository.CustomerRepository;

@Configuration
public class CustomerConfig {

    @Bean
    ApplicationRunner runner(CustomerRepository repository) {
        return args -> {
            var names = Flux.just("Subodh", "Juli", "Nidhi", "Deeksha", "Harsh", "Bindiya", "Akash");
            final var customers = names.map(name -> new Customer(null, name)).flatMap(repository::save);
            customers.thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }
}
