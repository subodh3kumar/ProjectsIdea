package workshop.record;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "CUSTOMERS")
public record Customer(@Id Integer id, String name) {
}
