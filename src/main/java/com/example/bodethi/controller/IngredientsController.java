package com.example.bodethi.controller;

import com.example.bodethi.entity.ClassEntity;
import com.example.bodethi.entity.IngredientsEntity;
import com.example.bodethi.entity.SubjectEntity;
import com.example.bodethi.repository.ClassReponsitory;
import com.example.bodethi.repository.IngredientsReponsitory;
import com.example.bodethi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class IngredientsController {
    @Autowired
    private ClassReponsitory classRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private IngredientsReponsitory ingredientsReponsitory;
    public String removeVietnameseDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }
    @GetMapping("/admin/classes/ingredients")
    public String getAllIngredients(Model model) {
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<SubjectEntity> SubjectEntity = subjectRepository.findAll();
        model.addAttribute("SubjectEntity", SubjectEntity);
        List<IngredientsEntity> ingredientsEntity = ingredientsReponsitory.findAll();
        model.addAttribute("ingredientsEntity", ingredientsEntity);
        return "dashboard/ingredients/index";
    }
    @GetMapping("/admin/subjects/{subjectId}/ingredients")
    public String getSubjectByClass(Model model, @PathVariable("subjectId") Long subjectId) {
        List<SubjectEntity> subjectEntity = subjectRepository.findAll();
        model.addAttribute("subjectEntity", subjectEntity);
        model.addAttribute("subjectId",subjectId);
        SubjectEntity SubjectEntity = subjectRepository.findById(subjectId).orElse(null);
        if (SubjectEntity != null) {
            List<IngredientsEntity> ingredients = SubjectEntity.getIngredients(); // Lấy danh sách môn học từ lớp học
            model.addAttribute("ingredients", ingredients);
        }
        return "dashboard/ingredients/FindBySubject";
    }
    @PostMapping("/admin/classes/ingredients/save")
    public String SaveIngredientsBySubject(@RequestParam("subjectId") Long subjectId, @RequestParam("ingredientsName") String ingredientsName, @RequestParam("description") String description) {
        IngredientsEntity  ingredients = new IngredientsEntity();
        ingredients.setIngredientsName(ingredientsName);
        ingredients.setDescription(description);
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
        if (subjectEntity != null) {
            ingredients.setSubject_tbl(subjectEntity);
            ingredientsReponsitory.save(ingredients);
            return "redirect:/admin/classes/ingredients";
        } else {
            return "dashboard/ingredients/index";
        }
    }
    @PostMapping("/admin/classes/subject/ingredients/save")
    public String SaveIngredientsBySubjectId(@RequestParam("subjectId") Long subjectId, @RequestParam("ingredientsName") String ingredientsName, @RequestParam("description") String description) {
        IngredientsEntity  ingredients = new IngredientsEntity();
        ingredients.setIngredientsName(ingredientsName);
        ingredients.setDescription(description);
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).orElse(null);
        if (subjectEntity != null) {
            ingredients.setSubject_tbl(subjectEntity);
            ingredientsReponsitory.save(ingredients);
            return "redirect:/admin/subjects/" + subjectId + "/ingredients";
        } else {
            return "dashboard/ingredients/index";
        }
    }
    @PostMapping("/admin/ingredients/{id}/update")
    public String updateIngredientsEntity(@PathVariable("id") Long id,@RequestParam("subjectId") Long subjectId, @ModelAttribute IngredientsEntity updatedIngredientsEntity) {
        Optional<IngredientsEntity> IngredientsEntityOptional =  ingredientsReponsitory.findById(id);
        if (IngredientsEntityOptional.isPresent()) {
            IngredientsEntity IngredientsEntity = IngredientsEntityOptional.get();
            IngredientsEntity.setIngredientsName(updatedIngredientsEntity.getIngredientsName());
            IngredientsEntity.setDescription(updatedIngredientsEntity.getDescription());
            SubjectEntity SubjectEntity = subjectRepository.findById(subjectId).orElse(null);
            if (SubjectEntity != null) {
                IngredientsEntity.setSubject_tbl(SubjectEntity);
            }
            ingredientsReponsitory.save(IngredientsEntity);
        }
        return "redirect:/admin/classes/ingredients";
    }
    @PostMapping("/admin/ingredients-{id}/update")
    public String updateIngredientsEntitybyId(@PathVariable("id") Long id,@RequestParam("subjectId") Long subjectId, @ModelAttribute IngredientsEntity updatedIngredientsEntity) {
        Optional<IngredientsEntity> IngredientsEntityOptional =  ingredientsReponsitory.findById(id);
        if (IngredientsEntityOptional.isPresent()) {
            IngredientsEntity IngredientsEntity = IngredientsEntityOptional.get();
            IngredientsEntity.setIngredientsName(updatedIngredientsEntity.getIngredientsName());
            IngredientsEntity.setDescription(updatedIngredientsEntity.getDescription());
            SubjectEntity SubjectEntity = subjectRepository.findById(subjectId).orElse(null);
            if (SubjectEntity != null) {
                IngredientsEntity.setSubject_tbl(SubjectEntity);
            }
            ingredientsReponsitory.save(IngredientsEntity);
        }
        return "redirect:/admin/subjects-" + subjectId + "/ingredients";
    }
    @GetMapping("/admin/ingredients/{id}/delete")
    public String deleteClassEntity(@PathVariable("id") Long id) {
        ingredientsReponsitory.deleteById(id);
        return "redirect:/admin/classes/ingredients";
    }
    @GetMapping("/admin/ingredients-{id}/delete")
    public String deleteClassEntitybyiD(@RequestParam("subjectId") Long subjectId,@PathVariable("id") Long id) {
        ingredientsReponsitory.deleteById(id);
        return "redirect:/admin/subjects-" + subjectId + "/ingredients";
    }
}
