package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lesson_tbl")
public class LessonEntity extends BaseEntity{
    @NotNull
    @Column(name = "lesson_name")
    private String lessonName;

    @Column
    private String description;
    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private ChapterEntity chapter_tbl;

    @OneToMany(mappedBy = "lesson_tbl", cascade = CascadeType.ALL)
    private List<ContentLessonEntity> content_lessons = new ArrayList<>();

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChapterEntity getChapter_tbl() {
        return chapter_tbl;
    }

    public void setChapter_tbl(ChapterEntity chapter_tbl) {
        this.chapter_tbl = chapter_tbl;
    }

    public List<ContentLessonEntity> getContent_lessons() {
        return content_lessons;
    }

    public void setContent_lessons(List<ContentLessonEntity> content_lessons) {
        this.content_lessons = content_lessons;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
