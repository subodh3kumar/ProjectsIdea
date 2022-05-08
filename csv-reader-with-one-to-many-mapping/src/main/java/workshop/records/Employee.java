package workshop.records;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Employee {
    @CsvBindByName(column = "firstname", required = true)
    private String firstName;

    @CsvBindByName(column = "lastname", required = true)
    private String lastName;

    @CsvBindByName
    private String email;

    @CsvBindByName(required = true)
    private String profession;

    @CsvBindByName
    private String city;

    @CsvBindByName
    private String country;
}
