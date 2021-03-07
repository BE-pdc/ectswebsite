package com.ap.ectswebsite.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Program implements Serializable, Comparable<Program> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Specialization> specializations;
    private String name;
    private String programCode;
    @OneToMany(cascade = CascadeType.ALL)// for future use
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Traject> trajects;
    private String programType;
    private int program;
    private String fieldOfStudy;
    private String schoolYear;



    public Program() {
    }

    public Program(Set<Specialization> specializations, String name, String programCode, Set<Traject> trajects, String programType, int program, String fieldOfStudy, String s) {
        this.specializations = specializations;
        this.name = name;
        this.programCode = programCode;
        this.trajects = trajects;
        this.programType = programType;
        this.program = program;
        this.fieldOfStudy = fieldOfStudy;
        this.schoolYear = s;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Set<Traject> getTrajects() {
        return trajects;
    }

    public void setTrajects(Set<Traject> trajects) {
        this.trajects = trajects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Program{" +
                "Id=" + Id +
                ", specializations=" + specializations +
                ", name='" + name + '\'' +
                ", programCode='" + programCode + '\'' +
                ", trajects=" + trajects +
                ", programType='" + programType + '\'' +
                ", program=" + program +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                '}';
    }

    @Override
    public int compareTo(Program program) {
        return this.getName().compareTo(program.getName());
    }
}
