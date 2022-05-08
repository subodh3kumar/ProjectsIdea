package workshop.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import workshop.record.Customer;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
