package com.ap.ectswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrajectDto {
    @JsonProperty("Deeltrajecten")
    private YearDto[] yearDtos;
    @JsonProperty("Naam")
    private String name;
    @JsonProperty("Orde")
    private int orde;
    @JsonProperty("P_opltraject")
    private int opltraject;
    @JsonProperty("Schooljaar")
    private String schoolYear;

    public TrajectDto() {
    }

    public TrajectDto(YearDto[] yearDtos, String name, int orde, int opltraject, String schoolYear) {
        this.yearDtos = yearDtos;
        this.name = name;
        this.orde = orde;
        this.opltraject = opltraject;
        this.schoolYear = schoolYear;
    }

    public YearDto[] getYearDtos() {
        return yearDtos;
    }

    public void setYearDtos(YearDto[] yearDtos) {
        this.yearDtos = yearDtos;
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
        return "TrajectDto{" +
                "yearDtos=" + Arrays.toString(yearDtos) +
                ", name='" + name + '\'' +
                ", orde=" + orde +
                ", opltraject=" + opltraject +
                ", schoolYear='" + schoolYear + '\'' +
                '}';
    }
}
