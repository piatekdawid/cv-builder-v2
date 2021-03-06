package com.piatekd.cvbuilder_v2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "experience")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Experience implements Comparable<Experience>{

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(dateStarted, that.dateStarted) &&
                Objects.equals(dateEnded, that.dateEnded) &&
                Objects.equals(place, that.place) &&
                Objects.equals(position, that.position) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(shortDescription, that.shortDescription) &&
                Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateStarted, dateEnded, place, position, companyName, shortDescription, person);
    }

    @Override
    public int compareTo(Experience o) {
        return Comparator.comparing(Experience::getCompanyName)
                .thenComparing(Experience::getPosition)
                .thenComparing(Experience::getPlace)
                .thenComparing(Experience::getShortDescription, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Experience::getDateStarted, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(Experience::getDateEnded, Comparator.nullsLast(Comparator.naturalOrder()))
                .compare(this, o);
    }
}
