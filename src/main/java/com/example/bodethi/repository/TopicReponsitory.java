package com.example.bodethi.repository;

import com.example.bodethi.entity.LessonEntity;
import com.example.bodethi.entity.SubjectEntity;
import com.example.bodethi.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicReponsitory extends JpaRepository<TopicEntity,Long> {
    @Query("SELECT t FROM TopicEntity t, IngredientsEntity  i WHERE t.ingredients_tbl.id = :ingredientsId ")
    List<TopicEntity> findFirst4IngredientsByIngredientsID(@Param("ingredientsId") Integer  ingredientsId);
    Optional<TopicEntity> findByTopicName(String TopicName);
}
