package com.example.bodethi.repository;

import com.example.bodethi.entity.ChapterEntity;
import com.example.bodethi.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChapterReponsitory extends JpaRepository<ChapterEntity,Long> {
    @Query("SELECT c FROM ChapterEntity c, IngredientsEntity  i WHERE c.ingredients_tbl.id = :ingredientsId ")
    List<ChapterEntity> finfbyIngredientsId(@Param("ingredientsId") Integer  ingredientsId);
}
