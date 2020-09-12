package com.piatekd.cvbuilder_v2.controller;

import com.piatekd.cvbuilder_v2.entity.ForeignLanguage;
import com.piatekd.cvbuilder_v2.entity.Person;
import com.piatekd.cvbuilder_v2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/person")
@CrossOrigin(origins = "http://localhost:4200")
@Transactional
public class LanguageController {


    @Autowired
    private PersonService service;

    @PostMapping("/{id}/language/")
    public Set<ForeignLanguage> addLanguage(@PathVariable Long id, @RequestBody ForeignLanguage language){
            Person person = service.findPersonById(id);
            person.addLanguage(language);
            service.save(person);
            return person.getLanguageSet();
    }
    @GetMapping("/{id}/language/")
    public Set<ForeignLanguage> getLanguages(@PathVariable Long id){
        Person person = service.findPersonById(id);
        return person.getLanguageSet();
    }

    @DeleteMapping("/{id}/language/{languageid}")
    public ResponseEntity<Set<ForeignLanguage>> removeLanguage(@PathVariable Long id, @PathVariable Long languageid){
        service.deleteLanguage(languageid);
        return ResponseEntity.ok()
                .body(service.findPersonById(id).getLanguageSet());
}
}
