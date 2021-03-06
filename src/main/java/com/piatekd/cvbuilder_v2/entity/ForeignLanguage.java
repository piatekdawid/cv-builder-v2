package com.piatekd.cvbuilder_v2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "language")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguage implements Comparable<ForeignLanguage> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    private String language;

    private String proficiency;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForeignLanguage language1 = (ForeignLanguage) o;
        return Objects.equals(language, language1.language) &&
                Objects.equals(proficiency, language1.proficiency) &&
                Objects.equals(person, language1.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, proficiency, person);
    }

    @Override
    public int compareTo(ForeignLanguage o) {
        return Comparator.comparing(ForeignLanguage::getLanguage)
                .thenComparing(ForeignLanguage::getProficiency)
                .compare(this, o);
    }
}
