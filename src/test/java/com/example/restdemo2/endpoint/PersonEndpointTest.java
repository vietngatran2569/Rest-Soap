package com.example.restdemo2.endpoint;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.dto.PersonDTO;
import com.example.restdemo2.repository.PersonRepository;
import com.example.restdemo2.service.PersonService;
import com.example.restdemo2.specification.PersonSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Date;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonEndpoint.class)
class PersonEndpointTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;

    //@Test
    void shouldTestFunction_getAllPeople() throws Exception {
        Person person = new Person(1L, "Ly", 23, 1500.0, new Date(), Person.Status.ACTIVE);
        PersonSpecification specification = PersonSpecification.spec();

        Optional.ofNullable("Ly").ifPresent(specification::byName);
        Optional.ofNullable("ACTIVE").ifPresent(s -> specification.byStatus(Person.Status.valueOf(s)));
        Optional.ofNullable(1L).ifPresent(s -> specification.byId(1L));

        Page<Person> personPage = personService.getAllPerson(person.getName(), person.getStatus().name(), 1, 10, person.getId());
        given(personService.getAllPerson(person.getName(), person.getStatus().name(), 1, 10, person.getId())).willReturn(personPage);

        MvcResult mvcResult = mockMvc.perform(get("/api_v1/person"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldTestFunction_getPersonById() throws Exception {
        Person person = new Person(1L, "Ly", 23, 1500.0, new Date(), Person.Status.ACTIVE);
        given(personService.getOne(1L)).willReturn(person);
        MvcResult mvcResult = mockMvc.perform(get("/api_v1/person/{id}", 1))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void shouldTestFunction_updatePerson() throws Exception {
        Person person = new Person(1L, "Ly", 23, 1500.0, new Date(), Person.Status.ACTIVE);
        person.setName("HOA");
        given(personService.updatePerson(person).getName()).willReturn("HOA");

        MvcResult mvcResult = mockMvc.perform(post("/api_v1/person/update"))
                .andExpect(status().isOk())
                .andReturn();
        Assertions.assertEquals(personService.updatePerson(person).getName(), "HOA");
    }
}
