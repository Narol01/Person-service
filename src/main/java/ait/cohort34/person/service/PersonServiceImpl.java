package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.*;
import ait.cohort34.person.dto.exception.PersonNotFoundException;
import ait.cohort34.person.model.Address;
import ait.cohort34.person.model.Child;
import ait.cohort34.person.model.Employee;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {


    final PersonRepository personRepository;
    final ModelMapper modelMapper;
    final PersonModelDtoMapper mapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
            personRepository.save( mapper.mapToModel(personDto));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        return mapper.mapToDto(person);
    }

    @Override
    public PersonDto[] findPersonsByCity(String city) {
        return personRepository.findByAddressCityIgnoreCase(city)
                .map(p->modelMapper.map(p,PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Override
    public PersonDto[] findPersonsByAgesBetween(Integer minAge, Integer maxAge) {
        LocalDate from=LocalDate.now().minusYears(maxAge);
        LocalDate to=LocalDate.now().minusYears(minAge);
        return personRepository.findByBirthDateBetween(from,to)
                .map(p->modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] findPersonsByFirstName(String firstName) {
        return personRepository.findByNameIgnoreCase(firstName)
                .map(p->modelMapper.map(p,PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional
    @Override
    public PersonDto updatePerson(Integer id, String firsName) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(firsName);
//        personRepository.save(person); не нужно если есть анотация транзакции
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<CityPopulationDto> getCityPopulation() {
        return personRepository.getCitiesPopulation();
    }

    @Transactional
    @Override
    public PersonDto updateAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(modelMapper.map(addressDto, Address.class));
//        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }
    @Transactional
    @Override
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }
    @Transactional(readOnly = true)
    @Override
    public EmployeeDto[] findEmployeesBySalary(Integer min, Integer max) {
        return personRepository.findEmployeesBySalaryBetween(min,max)
                .map(e->modelMapper.map(e, EmployeeDto.class))
                .toArray(EmployeeDto[]::new);
    }
    @Transactional(readOnly = true)
    @Override
    public ChildDto[] getChildren() {
        return personRepository.findChildrenBy()
                .map(c->modelMapper.map(c, ChildDto.class))
                .toArray(ChildDto[]::new);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
if(personRepository.count()==0){
    Person person = new Person(1000,"John",LocalDate.of(1985,3,11),
            new Address("Berlin","Purim",18));
    Child child= new Child(2000,"Karl",LocalDate.of(2018,3,11),
            new Address("Hamburg","HauptStrasse",5),"Sunny");
    Employee employee = new Employee(3000,"Mary",LocalDate.of(1995,11,23),
            new Address("Bremen","PappelStrasse",28),"Motorolla",4500);
    personRepository.save(person);
    personRepository.save(child);
    personRepository.save(employee);

}
    }
}
