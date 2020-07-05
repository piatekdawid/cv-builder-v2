package com.piatekd.cvbuilder_v2.repository;

import com.piatekd.cvbuilder_v2.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person save(Person person);

    Person findPersonById(Long id);
}
