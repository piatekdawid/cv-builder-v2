package com.piatekd.cvbuilder_v2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "education")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "started_date")
    private String startedSchool;

    @Column(name = "finish_date")
    private String finishedSchool;

    private String course;

    private String city;

    private String degree;

    private String schoolName;

    @Column(name = "additional_info")
    private String additionalInfo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}