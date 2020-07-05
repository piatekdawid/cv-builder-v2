package com.piatekd.cvbuilder_v2.controller;

import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.itext.Generator;
import com.piatekd.cvbuilder_v2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private HttpSession session;

    @Autowired
    private Generator generator;

    private Person getPersonFromSession() {
        Person person = (Person) session.getAttribute("person");
        return person;
    }

    @PostMapping("/")
    public Person createPerson(@RequestBody Person person) {
        session.setAttribute("person", person);
        return service.save(person);
    }

    @GetMapping("/")
    public Person findByPerson() {
        return service.findByPerson(getPersonFromSession());
    }

    @PutMapping("/about-me")
    public Person addAboutMe(@RequestBody String aboutMe) {
        getPersonFromSession().setAboutMe(aboutMe);
        return service.save(getPersonFromSession());
    }

    @PutMapping("/skills")
    public Person addSkill(@RequestBody String skill) {
        getPersonFromSession().addSkill(skill);
        return service.save(getPersonFromSession());
    }

    @PutMapping("/hobbies")
    public Person addHobby(@RequestBody String hobby) {
        getPersonFromSession().addHobby(hobby);
        return service.save(getPersonFromSession());
    }

    @PutMapping("/profiles")
    public Person addProfile(@RequestBody String profile) {
        getPersonFromSession().addProfile(profile);
        return service.save(getPersonFromSession());
    }

    @GetMapping("/finalize")
    public String finalizing() throws Exception {
        Person person = getPersonFromSession();
        generator.generator(person);
        return "Resume succesfully created.";
    }

    @GetMapping(value = "/getResume", produces = "application/pdf")
    public ResponseEntity<byte[]> getResume() {

        Path path = Paths.get("src/main/resources/pdfs/resume-DawidPiatek.pdf");
        byte[] pdfContents = null;
        try {
            pdfContents = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = "resume-DawidPiatek.pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=" + fileName);
        headers.setContentDispositionFormData("inline", fileName);
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
                pdfContents, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/test")
    public String test() {
        return (String) session.getAttribute("path");
    }

}
