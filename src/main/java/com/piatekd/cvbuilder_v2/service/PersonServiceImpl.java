package com.piatekd.cvbuilder_v2.service;

import com.piatekd.cvbuilder_v2.entity.*;
import com.piatekd.cvbuilder_v2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl {
    
    private final PersonRepository repository;
    private final LanguageRepository languageRepository;
    private final AchievementRepository achievementRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;

    public PersonServiceImpl(PersonRepository repository, LanguageRepository languageRepository, AchievementRepository achievementRepository, EducationRepository educationRepository, ExperienceRepository experienceRepository) {
        this.repository = repository;
        this.languageRepository = languageRepository;
        this.achievementRepository = achievementRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
    }

    public List<ForeignLanguage> deleteLanguage(Long id){
        return languageRepository.deleteLanguageById(id);
    }


    public Person save(Person person) {
        return repository.save(person);
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
