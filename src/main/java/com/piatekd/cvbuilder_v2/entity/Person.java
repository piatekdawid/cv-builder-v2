package com.piatekd.cvbuilder_v2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "personal_information")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    private String city;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<Education> educationSet = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<Experience> experienceSet = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<Achievement> achievementSet = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Set<ForeignLanguage> languageSet = new HashSet<>();

    @ElementCollection
    private List<String> hobbies = new ArrayList<>();

    @ElementCollection
    private List<String> skills = new ArrayList<>();

    @ElementCollection
    private List<String> profiles = new ArrayList<>();

    private String aboutMe;

    @Column(name = "clauzule", columnDefinition = "TEXT")
    private String clauzule;

    /*@Lob
    private byte[] photo;

    @Lob
    private byte[] pdf;*/

    public void addSkill(String skill) {
        skills.add(skill);
    }

    public void addProfile(String profile) {
        profiles.add(profile);
    }

    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }

    public void addEducation(Education education) {
        educationSet.add(education);
        education.setPerson(this);

    }

    public void removeEducation(Education education) {
        educationSet.remove(education);
        education.setPerson(null);
    }

    public void addExperience(Experience experience) {
        experienceSet.add(experience);
        experience.setPerson(this);

    }

    public void removeExperience(Experience experience) {
        experienceSet.remove(experience);
        experience.setPerson(null);
    }

    public void addAchievement(Achievement achievement) {
        achievementSet.add(achievement);
        achievement.setPerson(this);
    }

    public void removeAchievement(Achievement achievement) {
        achievementSet.remove(achievement);
        achievement.setPerson(null);
    }

    public void addLanguage(ForeignLanguage language) {
        languageSet.add(language);
        language.setPerson(this);
    }

    public void removeLanguage(ForeignLanguage language) {
        languageSet.remove(language);
        language.setPerson(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(address, person.address) &&
                Objects.equals(zipCode, person.zipCode) &&
                Objects.equals(city, person.city) &&
                Objects.equals(email, person.email) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                Objects.equals(educationSet, person.educationSet) &&
                Objects.equals(experienceSet, person.experienceSet) &&
                Objects.equals(achievementSet, person.achievementSet) &&
                Objects.equals(languageSet, person.languageSet) &&
                Objects.equals(hobbies, person.hobbies) &&
                Objects.equals(skills, person.skills) &&
                Objects.equals(aboutMe, person.aboutMe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, zipCode, city, email, phoneNumber, educationSet, experienceSet, achievementSet, languageSet, hobbies, skills, aboutMe);
    }
}