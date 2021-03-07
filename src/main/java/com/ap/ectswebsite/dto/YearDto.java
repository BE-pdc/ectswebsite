package com.ap.ectswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class YearDto {
    @JsonProperty("Blokken")
    private BlockDto[] blockDto;
    @JsonProperty("Naam")
    private String name;
    @JsonProperty("Opleidingsonderdelen")
    private CourseDto[] courseDtos;
    @JsonProperty("Orde")
    private int orde;
    @JsonProperty("P_deeltraject")
    private int traject;

    public YearDto() {
    }

    public YearDto(BlockDto[] blockDto, String name, CourseDto[] courseDtos, int orde, int p_traject) {
        this.blockDto = blockDto;
        this.name = name;
        this.courseDtos = courseDtos;
        this.orde = orde;
        this.traject = p_traject;
    }

    public BlockDto[] getBlockDto() {
        return blockDto;
    }

    public void setBlockDto(BlockDto[] blockDto) {
        this.blockDto = blockDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseDto[] getCourseDtos() {
        return courseDtos;
    }

    public void setCourseDtos(CourseDto[] courseDtos) {
        this.courseDtos = courseDtos;
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
}
