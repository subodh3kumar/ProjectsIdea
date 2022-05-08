package workshop.models;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class AddressCsvBean {
    @CsvBindByName(column = "first_name", required = true)
    private String firstName;

    @CsvBindByName(column = "last_name", required = true)
    private String lastName;

    @CsvBindByName(column = "company_name", required = true)
    private String companyName;

    @CsvBindByName
    private String address;

    @CsvBindByName
    private String city;

    @CsvBindByName
    private String state;

    @CsvBindByName
    private String post;

    @CsvBindByName
    private String phone1;

    @CsvBindByName
    private String phone2;

    @CsvBindByName
    private String email;

    @CsvBindByName
    private String web;
}
