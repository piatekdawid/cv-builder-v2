package com.piatekd.cvbuilder_v2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "education")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education{


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
}
