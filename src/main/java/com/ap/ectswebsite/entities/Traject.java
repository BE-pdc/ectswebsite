package com.ap.ectswebsite.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Traject implements Serializable, Comparable<Traject> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(cascade= CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Year> years;
    private String name;
    @Nullable
    private int orde;
    private int opltraject;
    private String schoolYear;

    public Traject(Set<Year> years, String name, @Nullable int orde, int opltraject, String schoolYear) {
        this.years = years;
        this.name = name;
        this.orde = orde;
        this.opltraject = opltraject;
        this.schoolYear = schoolYear;
    }

    public Traject() {
    }

    public Set<Year> getYears() {
        return years;
    }

    public void setYears(Set<Year> years) {
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrde() {
        return orde;
    }

    public void setOrde(int orde) {
        this.orde = orde;
    }

    public int getOpltraject() {
        return opltraject;
    }

    public void setOpltraject(int opltraject) {
        this.opltraject = opltraject;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Traject{" +
                "Id=" + Id +
                ", years=" + years +
                ", name='" + name + '\'' +
                ", orde=" + orde +
                ", opltraject=" + opltraject +
                ", schoolYear='" + schoolYear + '\'' +
                '}';
    }

    @Override
    public int compareTo(Traject traject) {
        return this.getOrde() - traject.getOrde();
    }
}
