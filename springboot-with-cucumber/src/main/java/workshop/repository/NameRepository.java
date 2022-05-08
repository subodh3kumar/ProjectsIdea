package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.model.Name;

public interface NameRepository extends JpaRepository<Name, Integer> {
}
