package com.piatekd.cvbuilder_v2.entity;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person;

    @BeforeEach
    void initializePerson(){
        this.person = new Person();
    }
    @AfterEach
    void cleanUp(){
        this.person = null;
    }

    @Test
    void addSkill() {
        String skill1 = "someValue";
        person.addSkill(skill1);
        assertSame(skill1, person.getSkills().get(0));
    }

    @Test
    void addProfile() {
        String profile = "someValue";
        person.addProfile(profile);
        assertSame(profile, person.getProfiles().get(0));
    }

    @Test
    void addHobby() {
        String hobby = "someValue";
        person.addHobby(hobby);
        assertSame(hobby, person.getHobbies().get(0));
    }

    @Test
    void removeHobby() {
        String hobby = "someValue";
        person.addHobby(hobby);
        assertThat(person.getHobbies().size(), equalTo(1));
        person.removeHobby(hobby);
        assertThat(person.getHobbies().size(), equalTo(0));
    }

    @Test
    void removeSkill() {
        String skill = "someValue";
        person.addSkill(skill);
        assertThat(person.getSkills().size(), equalTo(1));
        person.removeSkill(skill);
        assertThat(person.getSkills().size(), equalTo(0));
    }

    @Test
    void removeProfile() {
        String profile = "someValue";
        person.addProfile(profile);
        assertThat(person.getProfiles().size(), equalTo(1));
        person.removeProfile(profile);
        assertThat(person.getProfiles().size(), equalTo(0));
    }

    @Test
    @Disabled("Not implemented yet.")
    void addEducation() {
    }

    @Test
    @Disabled("Not implemented yet.")
    void removeEducation() {
    }

    @Test
    @Disabled("Not implemented yet.")
    void addExperience() {
    }

    @Test
    @Disabled("Not implemented yet.")
    void removeExperience() {
    }

    @Test
    @Disabled("Not implemented yet.")
    void addAchievement() {
    }

    @Test
    @Disabled("Not implemented yet.")
    void removeAchievement() {
    }

    @Test
    @Disabled("Not implemented yet.")
    void addLanguage() {
    }

    @Test
    @Disabled("Not implemented yet.")
    void removeLanguage() {
    }
}