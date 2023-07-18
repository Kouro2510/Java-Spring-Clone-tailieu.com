package com.example.bodethi.controller;

import com.example.bodethi.entity.ClassEntity;
import com.example.bodethi.repository.ClassReponsitory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller

public class ClassController {

    private final ClassReponsitory classReponsitory;

    public ClassController(ClassReponsitory classReponsitory) {
        this.classReponsitory = classReponsitory;
    }

    @GetMapping("/admin/classes")
    public String classList(Model model) {
        List<ClassEntity> classEntities = classReponsitory.findAll();
        model.addAttribute("classEntities", classEntities);
        model.addAttribute("classEntity", new ClassEntity());
        return "dashboard/Home";
    }


    @PostMapping("/admin/classes/save")
    public String saveClassEntity(@ModelAttribute ClassEntity classEntity,@RequestParam("classname") String className) {
        String normalized = removeVietnameseDiacritics(className);
        String formattedClassName = StringUtils.replaceChars(normalized, ' ', '-');
        classEntity.setUrl( formattedClassName.toLowerCase());
        classReponsitory.save(classEntity);
        return "redirect:/admin/classes";
    }
    public String removeVietnameseDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }
    @GetMapping("/admin/classes/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<ClassEntity> classEntityOptional = classReponsitory.findById(id);
        if (classEntityOptional.isPresent()) {
            ClassEntity classEntity = classEntityOptional.get();
            model.addAttribute("classEntity", classEntity);
            return "dashboard/Home";
        } else {
            return "redirect:/admin/classes";
        }
    }


    @PostMapping("/admin/classes/{id}/update")
    public String updateClassEntity(@PathVariable("id") Long id, @ModelAttribute ClassEntity updatedClassEntity) {
        Optional<ClassEntity> classEntityOptional = classReponsitory.findById(id);
        if (classEntityOptional.isPresent()) {
            ClassEntity classEntity = classEntityOptional.get();
            classEntity.setClassname(updatedClassEntity.getClassname());
            classEntity.setDescription(updatedClassEntity.getDescription());
            String normalized = removeVietnameseDiacritics(updatedClassEntity.getClassname());
            String formattedClassName = StringUtils.replaceChars(normalized, ' ', '-');
            classEntity.setUrl(formattedClassName.toLowerCase());
            classReponsitory.save(classEntity);
        }
        return "redirect:/admin/classes";
    }

    @GetMapping("/admin/classes/{id}/delete")
    public String deleteClassEntity(@PathVariable("id") Long id) {
        classReponsitory.deleteById(id);
        return "redirect:/admin/classes";
    }
}
