package workshop.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class EmployeeEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String profession;

    @Column
    private String city;

    @Column
    private String country;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private CsvFeedEntity csvFeed;
}
