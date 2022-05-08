package workshop.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
public class AddressEntity {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
    @Column(name = "address_id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "company_name")
    private String companyName;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String post;

    @Column
    private String phone1;

    @Column
    private String phone2;

    @Column
    private String email;

    @Column
    private String web;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private CsvFeedEntity csvFeedEntity;
}
