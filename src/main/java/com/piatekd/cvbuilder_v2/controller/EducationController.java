package com.piatekd.cvbuilder_v2.controller;

import com.piatekd.cvbuilder_v2.entity.Education;
import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/person/education")
public class EducationController {


    @Autowired
    private PersonService service;

    @Autowired
    private HttpSession session;

    private Person getPersonFromSession() {
        Person person = (Person) session.getAttribute("person");
        return person;
    }

    @PostMapping("/")
    public Person addEducation(@RequestBody Education education) {
        getPersonFromSession().addEducation(education);
        return service.save(getPersonFromSession());
    }

}