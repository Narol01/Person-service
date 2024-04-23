package ait.cohort34.person.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeeDto extends PersonDto {
    String company;
    int salary;
    String type;
}
