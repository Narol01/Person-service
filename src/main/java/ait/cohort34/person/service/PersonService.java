package ait.cohort34.person.service;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.model.City;

import java.time.LocalDate;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);
    PersonDto findPersonById(Integer id);
    Iterable<PersonDto> findPersonsByCity(String city);
    Iterable<PersonDto> findPersonsByAgesBetween(Integer minAge, Integer maxAge);
    Iterable<PersonDto> findPersonsByFirstName(String firstName);
    PersonDto updatePerson(Integer id, String firsName);
    Iterable<City> getCityPopulation();
    PersonDto updateAddress(Integer id, AddressDto addressDto);
    PersonDto deletePerson(Integer id);
}
