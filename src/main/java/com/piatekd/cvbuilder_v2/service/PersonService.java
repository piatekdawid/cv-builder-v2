package com.piatekd.cvbuilder_v2.service;

import com.piatekd.cvbuilder_v2.entity.*;
import com.piatekd.cvbuilder_v2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    public List<ForeignLanguage> deleteLanguage(Long id){
        return languageRepository.deleteLanguageById(id);
    }


    public Person save(Person person) {
        return repository.save(person);
    }

    public Person findByPerson(Person person) {
        return repository.findPersonById(person.getId());
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Person findPersonById(Long id) {
        return repository.findPersonById(id);
    }

    public List<Achievement> deleteAchievement(Long id) {
        return achievementRepository.deleteAchievementById(id);
    }

    public List<Education> deleteEducation(Long id) {
        return educationRepository.deleteEducationById(id);
    }

    public List<Experience> deleteExperience(Long id){
        return experienceRepository.deleteExperienceById(id);
    }
}
