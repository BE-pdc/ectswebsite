package com.ap.ectswebsite.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProgramDto {
    @JsonProperty("Afstudeerrichtingen")
    private SpecializationDto[] specializationDtos;
    @JsonProperty("Naam")
    private String name;
    @JsonProperty("Oplcode")
    private String programCode;
    @JsonProperty("Opleidingstrajecten")
    private TrajectDto[] trajectDtos;
    @JsonProperty("Opleidingtype")
    private String programType;
    @JsonProperty("P_opleiding")
    private int program;
    @JsonProperty("Studiegebied")
    private String fieldOfStudy;

    public ProgramDto() {
    super();
    }

    public ProgramDto(SpecializationDto[] specializationDtos, String name, String programCode, TrajectDto[] trajectDtos, String programType, int p_program, String fieldOfStudy) {
        this.specializationDtos = specializationDtos;
        this.name = name;
        this.programCode = programCode;
        this.trajectDtos = trajectDtos;
        this.programType = programType;
        this.program = p_program;
        this.fieldOfStudy = fieldOfStudy;
    }

    public SpecializationDto[] getSpecializationDtos() {
        return specializationDtos;
    }

    public void setSpecializationDtos(SpecializationDto[] specializationDtos) {
        this.specializationDtos = specializationDtos;
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

    public TrajectDto[] getTrajectDtos() {
        return trajectDtos;
    }

    public void setTrajectDtos(TrajectDto[] trajectDtos) {
        this.trajectDtos = trajectDtos;
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
}
