package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class_tbl")
public class ClassEntity extends BaseEntity{
    @NotNull
    @Column
    private String classname;

    @Column
    private String description;
    @Column
    private String url;
    @OneToMany(mappedBy = "class_tbl", cascade = CascadeType.ALL)
    private List<SubjectEntity> subjects = new ArrayList<>();

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        this.subjects = subjects;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
