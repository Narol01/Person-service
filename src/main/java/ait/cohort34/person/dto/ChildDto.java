package ait.cohort34.person.dto;


import lombok.Getter;

import java.time.LocalDate;


@Getter
public class ChildDto extends PersonDto {
    String kindergarten;
    String type;

    public ChildDto(Integer id, String name, LocalDate birthDate, AddressDto address, String kindergarten, String type) {
        super(id, name, birthDate, address);
        this.kindergarten = kindergarten;
        this.type = type;
    }
}
