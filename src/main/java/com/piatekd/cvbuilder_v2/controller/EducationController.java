package com.piatekd.cvbuilder_v2.controller;

import com.piatekd.cvbuilder_v2.entity.Education;
import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
@Transactional
public class EducationController {


    @Autowired
    private PersonServiceImpl service;
    
    @PostMapping("/{id}/education/")
    public Set<Education> addEducation(@PathVariable Long id, @RequestBody Education education){
        Person person = service.findPersonById(id);
        person.addEducation(education);
        service.save(person);
        return person.getEducationSet();
    }
    @GetMapping("/{id}/education/")
    public Set<Education> getEducations(@PathVariable Long id){
        Person person = service.findPersonById(id);
        return person.getEducationSet();
    }

    @DeleteMapping("/{id}/education/{educationId}")
    public ResponseEntity<Set<Education>> removeEducation(@PathVariable Long id, @PathVariable Long educationId){
        service.deleteEducation(educationId);
        return ResponseEntity.ok()
                .body(service.findPersonById(id).getEducationSet());
    }

}
