package com.piatekd.cvbuilder_v2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "experience")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "started_date")
    private String dateStarted;

    @Column(name = "finish_date")
    private String dateEnded;

    private String place;

    private String position;

    @Column(name = "company")
    private String companyName;

    @Column(name = "short_description")
    private String shortDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

}