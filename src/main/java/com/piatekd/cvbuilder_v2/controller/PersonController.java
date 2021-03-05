package com.piatekd.cvbuilder_v2.controller;

import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.exceptions.NoPersonFoundException;
import com.piatekd.cvbuilder_v2.service.PersonServiceImpl;
import com.piatekd.cvbuilder_v2.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    private PersonServiceImpl service;

    @Autowired
    private Generator generator;

    @Value("${pdf.source}")
    private String src;

    @Value("${pdf.output}")
    private String dest;

    @PostMapping("/")
    public Person createPerson(@RequestBody Person person) {
        return service.save(person);
    }

    @GetMapping("/findAll")
    public List<Person> findAllPersons(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Person findByPerson(@PathVariable(value = "id") Long id) throws NoPersonFoundException {
        return service.findPersonById(id);
    }
    @PutMapping("/{id}/about-me")
    public Person addAboutMe(@PathVariable(value = "id") Long id, @RequestBody String aboutMe) {
        Person person = service.findPersonById(id);
        person.setAboutMe(aboutMe);
        return service.save(person);
    }

    @PutMapping("/{id}/skills")
    public List<String> addSkill(@PathVariable Long id, @RequestBody String skill) {
        Person person = service.findPersonById(id);
        person.addSkill(skill);
        person = service.save(person);
        return person.getSkills();
    }
    @GetMapping("/{id}/skills")
    public List<String> getSkills(@PathVariable Long id){
        Person person = service.findPersonById(id);
        return person.getSkills();
    }
    @DeleteMapping("/{id}/skills/{skill}")
    public List<String> deleteSkill(@PathVariable Long id, @PathVariable String skill){
        Person person = service.findPersonById(id);
        person.removeSkill(skill);
        person = service.save(person);
        return person.getSkills();
    }

    @PutMapping("/{id}/hobbies")
    public List<String> addHobby(@PathVariable Long id, @RequestBody String hobby) {
        Person person = service.findPersonById(id);
        person.addHobby(hobby);
        person = service.save(person);
        return person.getHobbies();
    }
    @GetMapping("/{id}/hobbies")
    public List<String> getHobbies(@PathVariable Long id){
        Person person = service.findPersonById(id);
        return person.getHobbies();
    }
    @DeleteMapping("/{id}/hobbies/{hobby}")
    public List<String> deleteHobby(@PathVariable Long id, @PathVariable String hobby){
        Person person = service.findPersonById(id);
        person.removeHobby(hobby);
        person = service.save(person);
        return person.getHobbies();
    }

    @PutMapping("/{id}/profiles")
    public List<String> addProfile(@PathVariable Long id, @RequestBody String profile) {
        Person person = service.findPersonById(id);
        person.addProfile(profile);
        person = service.save(person);
        return person.getProfiles();
    }

    @GetMapping("/{id}/profiles")
    public List<String> getProfiles(@PathVariable Long id){
        Person person = service.findPersonById(id);
        return person.getProfiles();
    }
    @DeleteMapping("/{id}/profiles/{profile}")
    public List<String> deleteProfile(@PathVariable Long id, @PathVariable String profile){
        Person person = service.findPersonById(id);
        person.removeProfile(profile);
        person = service.save(person);
        return person.getProfiles();
    }

    @GetMapping("/{id}/finalize")
    public String finalizing(@PathVariable Long id) throws Exception {
        Person person = service.findPersonById(id);
        generator.generator(person);
        return "Resume for" + person.getFirstName() + " " + person.getLastName() + "was successfully created.";
    }

    @GetMapping(value = "/{id}/getResume", produces = "application/pdf")
    public ResponseEntity<byte[]> getResume(@PathVariable Long id) throws NoPersonFoundException {

        Person person = service.findPersonById(id);
        if (person != null) {
            String fileName = "resume-" + person.getFirstName() + person.getLastName() + ".pdf";
            Path path = Paths.get(dest + fileName);
            byte[] pdfContents = null;
            try {
                pdfContents = Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }


            HttpHeaders headers = new HttpHeaders();
            headers.add("content-disposition", "attachment; filename=" + fileName);
            headers.setContentDispositionFormData("inline", fileName);
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(
                    pdfContents, headers, HttpStatus.OK);
            return response;
        } else {
            throw new NoPersonFoundException("No resume to download.");
        }

    }
}
