package workshop.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private String id;
    private String name;
    private String job;
    private int mgrId;
    private LocalDate hiredate;
    private double salary;
    private double commission;
    private int deptNo;
}
