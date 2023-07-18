package com.example.bodethi.dto;

import com.example.bodethi.entity.ClassEntity;
import com.example.bodethi.entity.SubjectEntity;

import java.util.List;

public class ClassSubjectDTO {
    private ClassEntity classEntity;
    private List<SubjectEntity> subjects;

    public ClassSubjectDTO(ClassEntity classEntity, List<SubjectEntity> subjects) {
        this.classEntity = classEntity;
        this.subjects = subjects;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public List<SubjectEntity> getSubjects() {
        return subjects;
    }
}
