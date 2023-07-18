package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "content_lesson_tbl")
public class ContentLessonEntity extends BaseEntity{
    @NotNull
    @Column(columnDefinition = "text")
    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson_tbl;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LessonEntity getLesson_tbl() {
        return lesson_tbl;
    }

    public void setLesson_tbl(LessonEntity lesson_tbl) {
        this.lesson_tbl = lesson_tbl;
    }
}
