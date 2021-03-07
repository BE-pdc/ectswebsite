package com.ap.ectswebsite.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Block implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Block> blocks;
    @Column(length = 2000)
    @JsonProperty("Naam")
    private String naam;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Course> courses;

    private int orde;
    private int type;
    @Column(length = 2000)
    private String description;
    @Column(length = 2000)
    private String descriptionOption;
    private int choiceOption;
    private int choicePackage;
    private String schoolYear;

    public Block() {
    }

    public Block(Set<Block> blocks, String naam, Set<Course> courses, int orde, int type, String description, String descriptionOption, int choiceOption, int choicePackage, String schoolYear) {
        this.blocks = blocks;
        this.naam = naam;
        this.courses = courses;
        this.orde = orde;
        this.type = type;
        this.description = description;
        this.descriptionOption = descriptionOption;
        this.choiceOption = choiceOption;
        this.choicePackage = choicePackage;
        this.schoolYear = schoolYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<Block> blocks) {
        this.blocks = blocks;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
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

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", blocks=" + blocks +
                ", naam='" + naam + '\'' +
                ", courses=" + courses +
                ", orde=" + orde +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", descriptionOption='" + descriptionOption + '\'' +
                ", choiceOption=" + choiceOption +
                ", choicePackage=" + choicePackage +
                '}';
    }
}
