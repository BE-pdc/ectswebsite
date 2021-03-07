package com.ap.ectswebsite.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class YearEng implements Serializable, Comparable<YearEng> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(cascade= CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<BlockEng> blocks;
    private String name;
    @OneToMany(cascade= CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<CourseEng> courses;
    private int orde;
    private int traject;
    private String schoolYear;

    public YearEng() {
    }

    public Set<BlockEng> getBlocks() {
        return blocks;
    }

    public YearEng(Set<BlockEng> blocks, String name, Set<CourseEng> courses, int orde, int traject, String schoolYear) {
        this.blocks = blocks;
        this.name = name;
        this.courses = courses;
        this.orde = orde;
        this.traject = traject;
        this.schoolYear = schoolYear;
    }

    public void setBlocks(Set<BlockEng> blocks) {
        this.blocks = blocks;
    }

    public Set<CourseEng> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseEng> courses) {
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
    public int compareTo(YearEng year) {
        return Integer.compare(this.getOrde(), year.getOrde());
    }
}
