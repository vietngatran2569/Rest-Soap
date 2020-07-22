package com.example.restdemo2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Double salary;
    private Date dob;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @JsonIgnore
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person(long id) {
        this.id = id;
    }

    public Person(Long id,String name, Integer age, Double salary, Date dob, Status status) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dob = dob;
        this.status = status;
    }

    public enum Status{
        ACTIVE,
        INACTIVE;
    }
}
