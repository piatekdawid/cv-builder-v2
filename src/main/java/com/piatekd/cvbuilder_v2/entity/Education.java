package com.piatekd.cvbuilder_v2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "education")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education implements Comparable<Education>{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "started_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startedSchool;


    @Column(name = "finish_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate finishedSchool;

    private String course;

    @Column(name = "city")
    private String city;

    private String degree;

    private String schoolName;

    @Column(name = "additional_info")
    private String additionalInfo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Objects.equals(startedSchool, education.startedSchool) &&
                Objects.equals(finishedSchool, education.finishedSchool) &&
                Objects.equals(course, education.course) &&
                Objects.equals(city, education.city) &&
                Objects.equals(degree, education.degree) &&
                Objects.equals(schoolName, education.schoolName) &&
                Objects.equals(additionalInfo, education.additionalInfo) &&
                Objects.equals(person, education.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startedSchool, finishedSchool, course, city, degree, schoolName, additionalInfo, person);
    }


    @Override
    public int compareTo(Education o) {
        return Comparator.comparing(Education::getStartedSchool)
                .thenComparing(Education::getFinishedSchool)
                .thenComparing(Education::getCourse)
                .thenComparing(Education::getDegree)
                .thenComparing(Education::getSchoolName)
                .thenComparing(Education::getCity)
                .compare(this, o);
    }


}
