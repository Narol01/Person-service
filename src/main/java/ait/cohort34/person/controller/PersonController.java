package ait.cohort34.person.controller;

import ait.cohort34.person.dto.*;
import ait.cohort34.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping("")
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }
    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }
    @GetMapping("/city/{city}")
    public PersonDto[] findPersonsByCity(@PathVariable String city) {
     return personService.findPersonsByCity(city);
    }
    @GetMapping("/ages/{minAge}/{maxAge}")
    public PersonDto[] findPersonsByAgesBeetween(@PathVariable Integer minAge,@PathVariable Integer maxAge) {
    return personService.findPersonsByAgesBetween(minAge,maxAge);
    }
    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePerson(@PathVariable Integer id,@PathVariable String name) {
        return personService.updatePerson(id, name);
    }
    @GetMapping("/name/{name}")
    public PersonDto[] findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByFirstName(name);
    }
    @GetMapping("/population/city")
    public Iterable<CityPopulationDto>  getPopularCities() {
        return personService.getCityPopulation();
    }
    @PutMapping("/{id}/address")
    public PersonDto updateAddress(@PathVariable Integer id,@RequestBody AddressDto address) {
        return personService.updateAddress(id,address);
    }
    @DeleteMapping("/{id}")
    public  PersonDto deletePersonById(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }

    @GetMapping("/children")
    public ChildDto[] getAllChildren() {
        return personService.findAllChildren();
    }

    @GetMapping("/salary/{minSalary}/{maxSalary}")
    public EmployeeDto[] getEmployeesBySalary(@PathVariable Double minSalary,@PathVariable Double maxSalary) {
        return personService.findEmployeesBySalaryBetween(minSalary,maxSalary);
    }

}
