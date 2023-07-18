package com.example.bodethi.repository;

import com.example.bodethi.entity.ContentLessonEntity;
import com.example.bodethi.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentLessonReponsitory extends JpaRepository<ContentLessonEntity, Long> {
    @Query("SELECT c FROM ContentLessonEntity c WHERE c.lesson_tbl = :lesson")
    ContentLessonEntity findByLesson(@Param("lesson") LessonEntity lesson);


}