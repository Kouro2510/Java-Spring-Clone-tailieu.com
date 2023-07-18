package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredients_tbl")
public class IngredientsEntity extends BaseEntity{
    @NotNull
    @Column(name = "ingredients_name")
    private String ingredientsName;

    @Column
    private String description;
    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject_tbl;

    @OneToMany(mappedBy = "ingredients_tbl", cascade = CascadeType.ALL)
    private List<ChapterEntity> chapters = new ArrayList<>();

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubjectEntity getSubject_tbl() {
        return subject_tbl;
    }

    public void setSubject_tbl(SubjectEntity subject_tbl) {
        this.subject_tbl = subject_tbl;
    }

    public List<ChapterEntity> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterEntity> chapters) {
        this.chapters = chapters;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
