package com.example.restdemo2.specification;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.domain.Person_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PersonSpecification {

    private final List<Specification<Person>> personSpecs = new ArrayList<>();

    public static PersonSpecification spec() {
        return new PersonSpecification();
    }

    // By Name
    public void byName(String name) {
        personSpecs.add(hasName(name));
    }

    private Specification<Person> hasName(String name) {
        return StringUtils.isEmpty(name) ? all() : (Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            return criteriaBuilder.like(root.get(Person_.NAME), "%" + name + "%");
        };
    }
    //

    // By Status
    public void byStatus(Person.Status status) {
        personSpecs.add(hasStatus(status));
    }

    private Specification<Person> hasStatus(Person.Status status) {
        return StringUtils.isEmpty(status) ? all() : (Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            return criteriaBuilder.equal(root.get(Person_.STATUS), status);
        };
    }
    //

    // By Id
    public void byId(Long id) {
        personSpecs.add(hasId(id));
    }

    private Specification<Person> hasId(Long id) {
        return StringUtils.isEmpty(id) ? all() : (Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            criteriaQuery.distinct(true);
            return criteriaBuilder.equal(root.get(Person_.ID), id);
        };
    }
    //

    private Specification<Person> all() {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
    }

    public Specification<Person> build() {
        return Specification.where(personSpecs.stream().reduce(all(), Specification::and));
    }
}
