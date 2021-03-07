package com.ap.ectswebsite.entities;


import javax.persistence.*;
import java.io.Serializable;
@Entity
public class Course implements Serializable, Comparable<Course> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String ects;
    private String name;
    private int orde;
    private int studypoints;
    private int traject;
    private int keuzeoptie;
    private String schoolYear;

    public Course() {
    }

    public Course(String ects, String name, int orde, int studypoints, String schoolYear) {
        this.ects = ects;
        this.name = name;
        this.orde = orde;
        this.studypoints = studypoints;
        this.schoolYear = schoolYear;
    }


    public String getEcts() {
        return ects;
    }

    public void setEcts(String ects) {
        this.ects = ects;
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

    public int getStudypoints() {
        return studypoints;
    }

    public void setStudypoints(int studypoints) {
        this.studypoints = studypoints;
    }

    public int getTraject() {
        return traject;
    }

    public void setTraject(int traject) {
        this.traject = traject;
    }

    public int getKeuzeoptie() {
        return keuzeoptie;
    }

    public void setKeuzeoptie(int keuzeoptie) {
        this.keuzeoptie = keuzeoptie;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Id=" + Id +
                ", ects='" + ects + '\'' +
                ", name='" + name + '\'' +
                ", orde=" + orde +
                ", studypoints=" + studypoints +
                '}';
    }

    @Override
    public int compareTo(Course course) {
        return this.getOrde() - course.getOrde();
    }
}
