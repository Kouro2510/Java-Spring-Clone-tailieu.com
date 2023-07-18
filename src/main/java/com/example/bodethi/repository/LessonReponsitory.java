package com.example.bodethi.repository;

import com.example.bodethi.entity.IngredientsEntity;
import com.example.bodethi.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LessonReponsitory extends JpaRepository<LessonEntity,Long> {
    @Query("SELECT l FROM LessonEntity l, ChapterEntity  c WHERE l.chapter_tbl.id = :chapterId ")
    List<LessonEntity> findBychapter_tbl(@Param("chapterId") Integer  chapterId);
    Optional<LessonEntity> findByLessonName(String lessonName);
}

