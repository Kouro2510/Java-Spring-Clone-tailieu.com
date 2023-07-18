package com.example.bodethi.dto;

import com.example.bodethi.entity.IngredientsEntity;
import com.example.bodethi.entity.SubjectEntity;

import java.util.List;

public class SubjectIngredients {
    private SubjectEntity subjectEntity;
    private List<IngredientsEntity> ingredients;
    private  List<Integer> ingredientIds;
    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public List<IngredientsEntity> getIngredientsEntity() {
        return ingredients;
    }

    public SubjectIngredients(SubjectEntity subjectEntity, List<IngredientsEntity> ingredients, List<Integer> ingredientIds) {
        this.subjectEntity = subjectEntity;
        this.ingredients = ingredients;
    }

    public List<Integer> getIngredientIds() {
        return ingredientIds;
    }

    public void setIngredientIds(List<Integer> ingredientIds) {
        this.ingredientIds = ingredientIds;
    }
}
