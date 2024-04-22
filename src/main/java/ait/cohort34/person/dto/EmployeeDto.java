package ait.cohort34.person.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeeDto extends PersonDto {
    String company;
    int salary;
    String type;

    public EmployeeDto(Integer id, String name, LocalDate birthDate, AddressDto address, String company, int salary, String type) {
        super(id, name, birthDate, address);
        this.company = company;
        this.salary = salary;
        this.type = type;
    }
}
