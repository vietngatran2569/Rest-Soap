package com.example.restdemo2.service;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.repository.PersonRepository;
import com.example.restdemo2.specification.PersonSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Page<Person> getAllPerson(String keyword, String status, int page, int limit, Long id){
        PersonSpecification specification = PersonSpecification.spec();

        Optional.ofNullable(keyword).ifPresent(specification::byName);
        Optional.ofNullable(status).ifPresent(s -> specification.byStatus(Person.Status.valueOf(s)));
        Optional.ofNullable(id).ifPresent(s -> specification.byId(id));

        return personRepository.findAll(specification.build(), PageRequest.of(page - 1, limit));
    }

    public Person getOne(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }
}
