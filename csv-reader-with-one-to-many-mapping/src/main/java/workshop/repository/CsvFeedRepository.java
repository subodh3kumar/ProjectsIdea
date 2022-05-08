package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workshop.entity.CsvFeedEntity;

import java.util.UUID;

public interface CsvFeedRepository extends JpaRepository<CsvFeedEntity, UUID> {
}
