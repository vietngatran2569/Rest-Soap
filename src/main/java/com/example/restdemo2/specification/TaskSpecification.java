package com.example.restdemo2.specification;

import com.example.restdemo2.domain.Person;
import com.example.restdemo2.domain.Person_;
import com.example.restdemo2.domain.Task;
import com.example.restdemo2.domain.Task_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    private final List<Specification<Task>> taskSpecs = new ArrayList<>();

    public static TaskSpecification spec() {
        return new TaskSpecification();
    }

    // Get Task By Person Id
    public void byPersonId(Long id) {
        taskSpecs.add(hasPersonId(id));
    }

    private Specification<Task> hasPersonId(Long id) {
        return StringUtils.isEmpty(id) ? all() : (root, criteriaQuery, criteriaBuilder) -> {
            Join<Task, Person> join = root.join(Task_.PERSON);
            criteriaQuery.distinct(true);
            return criteriaBuilder.equal(join.get(Person_.ID), id);
        };
    }
    //

    private Specification<Task> all() {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
    }

    public Specification<Task> build() {
        return Specification.where(taskSpecs.stream().reduce(all(), Specification::and));
    }
}
