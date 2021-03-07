package com.ap.ectswebsite.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpecializationDto {

    @JsonProperty("Afstudrichtcode")
    private String specilizationcode;
    @JsonProperty("Naam")
    private String name;
    @JsonProperty("p_oplafstudricht")
    private int specialization;

    public SpecializationDto() {
    }

    public SpecializationDto(String specilizationcode, String name, int p_specilization) {
        this.specilizationcode = specilizationcode;
        this.name = name;
        this.specialization = p_specilization;
    }

    public String getSpecilizationcode() {
        return specilizationcode;
    }

    public void setSpecilizationcode(String specilizationcode) {
        this.specilizationcode = specilizationcode;
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
    
}
