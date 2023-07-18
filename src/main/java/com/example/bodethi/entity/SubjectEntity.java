package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject_tbl")
public class SubjectEntity extends BaseEntity{
    @NotNull
    @Column(name = "subject_name")
    private String subjectName;

    @Column
    private String description;
    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity class_tbl;


    @OneToMany(mappedBy = "subject_tbl", cascade = CascadeType.ALL)
    private List<IngredientsEntity> ingredients = new ArrayList<>();

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClassEntity getClass_tbl() {
        return class_tbl;
    }

    public void setClass_tbl(ClassEntity class_tbl) {
        this.class_tbl = class_tbl;
    }

    public List<IngredientsEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
