package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.exception.PersonNotFoundException;
import ait.cohort34.person.model.City;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save( modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<PersonDto> findPersonsByCity(String city) {
        return null;
    }

    @Override
    public Iterable<PersonDto> findPersonsByAgesBetween(Integer minAge, Integer maxAge) {
        return null;
    }

    @Override
    public Iterable<PersonDto> findPersonsByFirstName(String firstName) {
        return null;
    }

    @Override
    public PersonDto updatePerson(Integer id, String firsName) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(firsName);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<City> getCityPopulation() {
        return null;
    }

    @Override
    public PersonDto updateAddress(Integer id, AddressDto addressDto) {
        return null;
    }

    @Override
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }
}
