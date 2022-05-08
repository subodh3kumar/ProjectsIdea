package workshop.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class CsvFeed {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "feed_id")
    private String id;

    @Column(name = "created_by")
    private String createdBy;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime timestamp;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "csvFeed", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Address> addressList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "csvFeed", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Employee> employeeList;
}
