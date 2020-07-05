package com.piatekd.cvbuilder_v2.controller;


import com.piatekd.cvbuilder_v2.entity.Achievement;
import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/person/achievement")
public class AchievementController {


    @Autowired
    private PersonService personService;

    @Autowired
    private HttpSession session;

    private Person getPersonFromSession() {
        Person person = (Person) session.getAttribute("person");
        return person;
    }

    @PostMapping("/")
    public Person addAchievement(@RequestBody Achievement achievement) {
        getPersonFromSession().addAchievement(achievement);
        return personService.save(getPersonFromSession());
    }

}