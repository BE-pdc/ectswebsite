package com.ap.ectswebsite.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class ProgramEng implements Serializable, Comparable<ProgramEng> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SpecializationEng> specializations;
    private String name;
    private String programCode;
    @OneToMany(cascade = CascadeType.ALL)// for future use
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<TrajectEng> trajects;
    private String programType;
    private int program;
    private String fieldOfStudy;
    private String schoolYear;



    public ProgramEng() {
    }

    public ProgramEng(Set<SpecializationEng> specializations, String name, String programCode, Set<TrajectEng> trajects, String programType, int program, String fieldOfStudy, String s) {
        this.specializations = specializations;
        this.name = name;
        this.programCode = programCode;
        this.trajects = trajects;
        this.programType = programType;
        this.program = program;
        this.fieldOfStudy = fieldOfStudy;
        this.schoolYear = s;
    }

    public Set<SpecializationEng> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<SpecializationEng> specializations) {
        this.specializations = specializations;
    }

    public Set<TrajectEng> getTrajects() {
        return trajects;
    }

    public void setTrajects(Set<TrajectEng> trajects) {
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
    public int compareTo(ProgramEng program) {
        return this.getName().compareTo(program.getName());
    }
}
