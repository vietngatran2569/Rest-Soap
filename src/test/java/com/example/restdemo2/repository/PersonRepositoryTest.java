package com.example.restdemo2.repository;

import com.example.restdemo2.domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void findPersonById() throws Exception {
        Person person = new Person(1L, "Hoa", 23, 200.0, new Date(), Person.Status.ACTIVE, null);
        personRepository.save(person);
        Person db_person1 = personRepository.getOne(1L);
        Assertions.assertThat(db_person1.getId()).isEqualTo(1L);
    }

    @Test
    public void savePersonTest() {
        Person person = new Person(1L, "Hoa", 23, 200.0, new Date(), Person.Status.ACTIVE);
        personRepository.save(person);
        Assertions.assertThat(person).isNotNull();
    }

    @Test
    public void findAllPersonTest() {
        List<Person> person = Arrays.asList(new Person(1L, "Hoa", 23, 200.0, new Date(), Person.Status.ACTIVE),
                new Person(2L, "Hoa", 23, 200.0, new Date(), Person.Status.ACTIVE));
        personRepository.saveAll(person);
        Assertions.assertThat(personRepository.findAll()).isNotNull();
        //Assertions.assertThat(personRepository.findAll().size()).isEqualTo(2);
    }


    //    @Test
    public void deletePerson() {
        List<Person> person = Arrays.asList(new Person(1L, "Hoa", 23, 200.0, new Date(), Person.Status.ACTIVE),
                new Person(2L, "hi", 23, 200.0, new Date(), Person.Status.ACTIVE));
        personRepository.saveAll(person);
        personRepository.deleteById(1L);
        Assertions.assertThat(personRepository.findAll().size()).isEqualTo(1);
    }
}
