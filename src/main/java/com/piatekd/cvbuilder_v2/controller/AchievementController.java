package com.piatekd.cvbuilder_v2.controller;


import com.piatekd.cvbuilder_v2.entity.Achievement;
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
public class AchievementController {


    @Autowired
    private PersonService service;

    @PostMapping("/{id}/achievement/")
    public Set<Achievement> addAchievement(@PathVariable Long id, @RequestBody Achievement achievement){
        Person person = service.findPersonById(id);
        person.addAchievement(achievement);
        service.save(person);
        return person.getAchievementSet();
    }
    @GetMapping("/{id}/achievement/")
    public Set<Achievement> getAchievements(@PathVariable Long id){
        Person person = service.findPersonById(id);
        return person.getAchievementSet();
    }

    @DeleteMapping("/{id}/achievement/{achievementId}")
    public ResponseEntity<Set<Achievement>> removeAchievement(@PathVariable Long id, @PathVariable Long achievementId){
        service.deleteAchievement(achievementId);
        return ResponseEntity.ok()
                .body(service.findPersonById(id).getAchievementSet());
    }

}
