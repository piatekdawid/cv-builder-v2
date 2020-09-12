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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateStarted;

    @Column(name = "finish_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateEnded;

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
