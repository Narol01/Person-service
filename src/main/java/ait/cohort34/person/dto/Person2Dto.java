package ait.cohort34.person.dto;

import java.time.LocalDate;

public class Person2Dto  extends PersonDto {
    String type;

    public Person2Dto(Integer id, String name, LocalDate birthDate, AddressDto address, String type) {
        super(id, name, birthDate, address);
        this.type = type;
    }
}
