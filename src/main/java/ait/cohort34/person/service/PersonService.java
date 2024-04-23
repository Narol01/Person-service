package ait.cohort34.person.service;

import ait.cohort34.person.dto.*;


public interface PersonService {
    Boolean addPerson(PersonDto personDto);
    PersonDto findPersonById(Integer id);
    PersonDto[] findPersonsByCity(String city);
    PersonDto[] findPersonsByAgesBetween(Integer minAge, Integer maxAge);
    PersonDto[] findPersonsByFirstName(String firstName);
    PersonDto updatePerson(Integer id, String firsName);
    Iterable<CityPopulationDto> getCityPopulation();
    PersonDto updateAddress(Integer id, AddressDto addressDto);
    PersonDto deletePerson(Integer id);
    EmployeeDto[] findEmployeesBySalary(Integer min, Integer max);

    ChildDto[] getChildren();
}
