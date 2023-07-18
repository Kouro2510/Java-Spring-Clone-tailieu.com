package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chapter_tbl")
public class ChapterEntity extends BaseEntity{
    @NotNull
    @Column(name = "chapter_name")
    private String chapterName;

    @Column
    private String description;
    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "ingredients_id")
    private IngredientsEntity ingredients_tbl;
    @OneToMany(mappedBy = "chapter_tbl")
    private List<LessonEntity> lessonEntities;
    @OneToMany(mappedBy = "chapter_tbl", cascade = CascadeType.ALL)
    private List<LessonEntity> lessons = new ArrayList<>();

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IngredientsEntity getIngredients_tbl() {
        return ingredients_tbl;
    }

    public void setIngredients_tbl(IngredientsEntity ingredients_tbl) {
        this.ingredients_tbl = ingredients_tbl;
    }

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    public List<LessonEntity> getLessonEntities() {
        return lessonEntities;
    }

    public void setLessonEntities(List<LessonEntity> lessonEntities) {
        this.lessonEntities = lessonEntities;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
