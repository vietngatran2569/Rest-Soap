package com.example.restdemo2.endpoint;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.dto.PersonDTO;
import com.example.restdemo2.endpoint.rest.RESTPagination;
import com.example.restdemo2.endpoint.rest.RESTResponse;
import com.example.restdemo2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api_v1/person")
@CrossOrigin(value = "*", allowedHeaders = "*")
@Validated
public class PersonEndpoint {

    @Autowired
    PersonService personService;

    @GetMapping
    public ResponseEntity<Object> getAllPeople(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "personId", required = false) Long personId
    ) {
        Page<Person> personPage = personService.getAllPerson(keyword, status, page, limit, personId);
        return new ResponseEntity<>(
                RESTResponse.Builder()
                        .setStatus(HttpStatus.OK.value())
                        .setMessage("Lấy danh sách thành công!")
                        .setDatas(personPage.getContent().stream().map(PersonDTO::new).collect(Collectors.toList()))
                        .setPagination(new RESTPagination(page, limit, personPage.getTotalPages(), personPage.getTotalElements())).build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable("id") Long id) {
        return new PersonDTO(personService.getOne(id));
    }

    @PostMapping("/update")
    public PersonDTO updatePerson(@Valid @RequestBody PersonDTO person) {
        return new PersonDTO(personService.updatePerson(person.toEntity()));
    }

//    @PostMapping("/person")
//    public Person create(@RequestBody @Validated Person person) {
//        return person;
//    }
}
