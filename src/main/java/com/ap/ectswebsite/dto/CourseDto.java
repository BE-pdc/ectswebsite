package com.ap.ectswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CourseDto {
    @JsonProperty("ECTS")
    private String ects;
    @JsonProperty("Naam")
    private String name;
    @JsonProperty("Orde")
    private int orde;
    @JsonProperty("Studiepunten")
    private int studypoints;

    public CourseDto() {
    }

    public CourseDto(String ects, String name, int orde, int studypoints) {
        this.ects = ects;
        this.name = name;
        this.orde = orde;
        this.studypoints = studypoints;
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
}
