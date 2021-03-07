package com.ap.ectswebsite.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BlockDto {

    @JsonProperty("Blokken")
    private BlockDto[] blockDtos;
    @JsonProperty("Naam")
    private String naam;
    @JsonProperty("Opleidingsonderdelen")
    private CourseDto[] courseDto;
    @JsonProperty("Orde")
    private int orde;
    @JsonProperty("Type")
    private int type;
    @JsonProperty("Uitleg")
    private String description;
    @JsonProperty("UitlegOptie")
    private String descriptionOption;
    @JsonProperty("p_keuzeoptie")
    private int choiceOption;
    @JsonProperty("p_keuzepakket")
    private int choicePackage;

    public BlockDto() {
    }

    public BlockDto(BlockDto[] blockDtos, String naam, CourseDto[] courseDto, int orde, int type, String description, String descriptionOption, int choiceOption, int choicePackage) {
        this.blockDtos = blockDtos;
        this.naam = naam;
        this.courseDto = courseDto;
        this.orde = orde;
        this.type = type;
        this.description = description;
        this.descriptionOption = descriptionOption;
        this.choiceOption = choiceOption;
        this.choicePackage = choicePackage;
    }

    public BlockDto[] getBlockDtos() {
        return blockDtos;
    }

    public void setBlockDtos(BlockDto[] blockDtos) {
        this.blockDtos = blockDtos;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public CourseDto[] getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto[] courseDto) {
        this.courseDto = courseDto;
    }

    public int getOrde() {
        return orde;
    }

    public void setOrde(int orde) {
        this.orde = orde;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionOption() {
        return descriptionOption;
    }

    public void setDescriptionOption(String descriptionOption) {
        this.descriptionOption = descriptionOption;
    }

    public int getChoiceOption() {
        return choiceOption;
    }

    public void setChoiceOption(int choiceOption) {
        this.choiceOption = choiceOption;
    }

    public int getChoicePackage() {
        return choicePackage;
    }

    public void setChoicePackage(int choicePackage) {
        this.choicePackage = choicePackage;
    }
}
