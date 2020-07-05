package com.piatekd.cvbuilder_v2.service;

import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;


    public Person save(Person person) {
        return repository.save(person);
    }

    public Person findByPerson(Person person) {
        return repository.findPersonById(person.getId());
    }
}
