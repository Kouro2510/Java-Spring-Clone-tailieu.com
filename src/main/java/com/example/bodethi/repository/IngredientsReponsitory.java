package com.example.bodethi.repository;

import com.example.bodethi.entity.IngredientsEntity;
import com.example.bodethi.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientsReponsitory extends JpaRepository<IngredientsEntity,Long> {
    @Query("SELECT i FROM SubjectEntity s, IngredientsEntity  i WHERE i.subject_tbl.id = :subjectID and i.subject_tbl.id = s.id")
    List<IngredientsEntity> findFirst4IngredientsBySubjectID(@Param("subjectID") Integer  subjectID);

    Optional<IngredientsEntity> findByIngredientsName(String ingredientsName);
}
