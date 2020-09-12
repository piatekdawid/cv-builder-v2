package com.piatekd.cvbuilder_v2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "language")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    private String language;

    private String proficiency;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;



}
