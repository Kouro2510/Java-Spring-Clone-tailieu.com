package com.example.bodethi.controller;

import com.example.bodethi.entity.ClassEntity;
import com.example.bodethi.entity.SubjectEntity;
import com.example.bodethi.repository.ClassReponsitory;
import com.example.bodethi.repository.SubjectRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
public class SubjectController {

    @Autowired
    private ClassReponsitory classRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    public String removeVietnameseDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }
    @GetMapping("/admin/subject")
    public String getAllSubject(Model model){
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<SubjectEntity> SubjectEntity = subjectRepository.findAll();
        model.addAttribute("SubjectEntity", SubjectEntity);
        return "dashboard/subject/Index";
    }
    @GetMapping("/admin/classes-{classId}/subjects")
    public String getSubjectByClass(Model model, @PathVariable("classId") Long classId) {
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        model.addAttribute("classId",classId);
        ClassEntity classEntity = classRepository.findById(classId).orElse(null);
        if (classEntity != null) {
            List<SubjectEntity> subjects = classEntity.getSubjects(); // Lấy danh sách môn học từ lớp học
            model.addAttribute("subjects", subjects);
        }

        return "dashboard/subject/FindByClass";
    }

    @PostMapping("/admin/classes/{classId}/subjects/save")
    public String addSubjectByClass(@PathVariable("classId") Long classId, @RequestParam("subjectName") String subjectName,@RequestParam("description") String description) {
        SubjectEntity subject = new SubjectEntity();
        String normalized = removeVietnameseDiacritics(subjectName);
        String formattedClassName = StringUtils.replaceChars(normalized, ' ', '-');
        subject.setUrl(formattedClassName.toLowerCase());
        subject.setSubjectName(subjectName);
        subject.setDescription(description);
        ClassEntity classEntity = classRepository.findById(classId).orElse(null);

        if (classEntity != null) {
            subject.setClass_tbl(classEntity);
            subjectRepository.save(subject);
            return "redirect:/admin/classes";
        } else {
            return "dashboard/subject/Index";
        }
    }

    @PostMapping("/admin/classes/subjects/save")
    public String SaveSubjectByClass(@RequestParam("classId") Long classId, @RequestParam("subjectName") String subjectName,@RequestParam("description") String description) {
        SubjectEntity subject = new SubjectEntity();
        String normalized = removeVietnameseDiacritics(subjectName);
        String formattedClassName = StringUtils.replaceChars(normalized, ' ', '-');
        subject.setUrl(formattedClassName.toLowerCase());
        subject.setSubjectName(subjectName);
        subject.setDescription(description);
        ClassEntity classEntity = classRepository.findById(classId).orElse(null);

        if (classEntity != null) {
            subject.setClass_tbl(classEntity);
            subjectRepository.save(subject);
            return "redirect:/admin/subject";
        } else {
            return "dashboard/subject/Index";
        }
    }
    @PostMapping("/admin/class/subjects/save")
    public String SaveSubjectByClassid(@RequestParam("classId") Long classId, @RequestParam("subjectName") String subjectName,@RequestParam("description") String description) {
        SubjectEntity subject = new SubjectEntity();
        subject.setSubjectName(subjectName);
        subject.setDescription(description);
        ClassEntity classEntity = classRepository.findById(classId).orElse(null);
        String normalized = removeVietnameseDiacritics(subjectName);
        String formattedClassName = StringUtils.replaceChars(normalized, ' ', '-');
        subject.setUrl(formattedClassName.toLowerCase());
        if (classEntity != null) {

            subject.setClass_tbl(classEntity);
            subjectRepository.save(subject);
            return "redirect:/admin/classes-" + classId + "/subjects";
        } else {
            return "error-page";
        }
    }
    @PostMapping("/admin/subject/{id}/update")
    public String updateSubjectEntity(@PathVariable("id") Long id, @RequestParam("classId") Long classId, @ModelAttribute SubjectEntity updatedSubjectEntity) {
        Optional<SubjectEntity> subjectEntityOptional =  subjectRepository.findById(id);
        if (subjectEntityOptional.isPresent()) {
            SubjectEntity SubjectEntity = subjectEntityOptional.get();
            SubjectEntity.setSubjectName(updatedSubjectEntity.getSubjectName());
            SubjectEntity.setDescription(updatedSubjectEntity.getDescription());
            String normalized = removeVietnameseDiacritics(updatedSubjectEntity.getSubjectName());
            String formattedClassName = StringUtils.replaceChars(normalized, ' ', '-');
            SubjectEntity.setUrl(formattedClassName.toLowerCase());
            ClassEntity classEntity = classRepository.findById(classId).orElse(null);
            if (classEntity != null) {
                SubjectEntity.setClass_tbl(classEntity);
            }
            subjectRepository.save(SubjectEntity);
        }
        return "redirect:/admin/subject";
    }
    @PostMapping("/admin/subject-{id}/update")
    public String updateSubjectEntitybyClass(@PathVariable("id") Long id, @RequestParam("classId") Long classId, @ModelAttribute SubjectEntity updatedSubjectEntity) {
        Optional<SubjectEntity> subjectEntityOptional =  subjectRepository.findById(id);
        if (subjectEntityOptional.isPresent()) {
            SubjectEntity SubjectEntity = subjectEntityOptional.get();
            SubjectEntity.setSubjectName(updatedSubjectEntity.getSubjectName());
            SubjectEntity.setDescription(updatedSubjectEntity.getDescription());
            String normalized = removeVietnameseDiacritics(updatedSubjectEntity.getSubjectName());
            String formattedClassName = StringUtils.replaceChars(normalized, ' ', '-');
            SubjectEntity.setUrl(formattedClassName.toLowerCase());
            ClassEntity classEntity = classRepository.findById(classId).orElse(null);
            if (classEntity != null) {
                SubjectEntity.setClass_tbl(classEntity);
            }
            subjectRepository.save(SubjectEntity);
        }
        return "redirect:/admin/classes-" + classId + "/subjects";
    }
    @GetMapping("/admin/subject/{id}/delete")
    public String deleteClassEntity(@PathVariable("id") Long id) {
        subjectRepository.deleteById(id);
        return "redirect:/admin/subject";
    }
    @GetMapping("/admin/subject-{id}/delete")
    public String deleteClassEntitybyClass(@PathVariable("id") Long id,@RequestParam("classId") Long classId) {
        System.out.println(classId);
        subjectRepository.deleteById(id);
        return "redirect:/admin/classes-" + classId + "/subjects";
    }
}
