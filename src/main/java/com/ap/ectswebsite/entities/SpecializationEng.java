package com.ap.ectswebsite.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class SpecializationEng implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String specilizationcode;
    private String name;
    private int specialization;
    private String schoolYear;

    public SpecializationEng() {
    }

    public SpecializationEng(String specilizationcode, String name, int p_specialization, String schoolYear) {
        this.specilizationcode = specilizationcode;
        this.name = name;
        this.specialization = p_specialization;
        this.schoolYear = schoolYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpecialization() {
        return specialization;
    }

    public void setSpecialization(int specialization) {
        this.specialization = specialization;
    }

    public String getSpecilizationcode() {
        return specilizationcode;
    }

    public void setSpecilizationcode(String specilizationcode) {
        this.specilizationcode = specilizationcode;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "Id=" + Id +
                ", specilizationcode='" + specilizationcode + '\'' +
                ", name='" + name + '\'' +
                ", specialization=" + specialization +
                '}';
    }
}
