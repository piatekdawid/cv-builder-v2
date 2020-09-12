package com.piatekd.cvbuilder_v2.controller;

import com.piatekd.cvbuilder_v2.entity.Experience;
import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
@Transactional
public class ExperienceController {


    @Autowired
    private PersonService service;

    @PostMapping("/{id}/experience/")
    public Set<Experience> addExperience(@PathVariable Long id, @RequestBody Experience experience){
        Person person = service.findPersonById(id);
        person.addExperience(experience);
        service.save(person);
        return person.getExperienceSet();
    }
    @GetMapping("/{id}/experience/")
    public Set<Experience> getExperiences(@PathVariable Long id){
        Person person = service.findPersonById(id);
        return person.getExperienceSet();
    }

    @DeleteMapping("/{id}/experience/{experienceId}")
    public ResponseEntity<Set<Experience>> removeExperience(@PathVariable Long id, @PathVariable Long experienceId){
        service.deleteExperience(experienceId);
        return ResponseEntity.ok()
                .body(service.findPersonById(id).getExperienceSet());
    }

}
