package com.ap.ectswebsite.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Year implements Serializable, Comparable<Year> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(cascade= CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Block> blocks;
    private String name;
    @OneToMany(cascade= CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Course> courses;
    private int orde;
    private int traject;
    private String schoolYear;

    public Year() {
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public Year(Set<Block> blocks, String name, Set<Course> courses, int orde, int traject, String schoolYear) {
        this.blocks = blocks;
        this.name = name;
        this.courses = courses;
        this.orde = orde;
        this.traject = traject;
        this.schoolYear = schoolYear;
    }

    public void setBlocks(Set<Block> blocks) {
        this.blocks = blocks;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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

    public int getTraject() {
        return traject;
    }

    public void setTraject(int traject) {
        this.traject = traject;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Year{" +
                "Id=" + Id +
                ", blocks=" + blocks +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                ", orde=" + orde +
                ", traject=" + traject +
                '}';
    }

    @Override
    public int compareTo(Year year) {
        return Integer.compare(this.getOrde(), year.getOrde());
    }
}
