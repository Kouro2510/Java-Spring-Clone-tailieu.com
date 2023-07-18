package com.example.bodethi.controller;

import com.example.bodethi.entity.*;
import com.example.bodethi.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LessonController {
    @Autowired
    private ClassReponsitory classRepository;
    @Autowired
    private ChapterReponsitory chapterReponsitory;
    @Autowired
    private LessonReponsitory lessonReponsitory;
    @Autowired
    private ContentLessonReponsitory contentLessonReponsitory;
    @GetMapping("/admin/class/subject/ingredients/chapter/lesson")
    public String getAllLesson(Model model){
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<ChapterEntity> chapterEntity = chapterReponsitory.findAll();
        model.addAttribute("chapterEntity", chapterEntity);
        List<LessonEntity> lessonEntities = lessonReponsitory.findAll();
        model.addAttribute("lessonEntities",lessonEntities);
        List<ContentLessonEntity> contentLessonEntities = contentLessonReponsitory.findAll();
        model.addAttribute("contentLessonEntities",contentLessonEntities);
        return "dashboard/lesson/index";
    }
    @GetMapping("/admin/class/subject/ingredients/chapter/lesson/new")
    public String CreateNewLesson(Model model){
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<ChapterEntity> chapterEntity = chapterReponsitory.findAll();
        model.addAttribute("chapterEntity", chapterEntity);
        return "dashboard/lesson/new";
    }
    @PostMapping("/admin/lesson/save")
    public String SaveNewLesson(@RequestParam("chapterId") Long chapterId, @RequestParam("lessonName") String lessonName, @RequestParam("description") String description,@RequestParam("content") String content){
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setLessonName(lessonName);
        lessonEntity.setDescription(description);
        ChapterEntity chapterEntity=chapterReponsitory.findById(chapterId).orElse(null);
        if (chapterEntity != null){
            lessonEntity.setChapter_tbl(chapterEntity);
        }
        lessonReponsitory.save(lessonEntity);
        ContentLessonEntity contentLessonEntity = new ContentLessonEntity();
        contentLessonEntity.setContents(content);
        contentLessonEntity.setLesson_tbl(lessonEntity);
        contentLessonReponsitory.save(contentLessonEntity);

        return "redirect:/admin/class/subject/ingredients/chapter/lesson";
    }
    @GetMapping("/admin/lesson/{lessonId}/edit")
    public String editLesson(@PathVariable Long lessonId, Model model) {
        LessonEntity lessonEntity = lessonReponsitory.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson ID"));
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);

        List<ChapterEntity> chapterEntity = chapterReponsitory.findAll();
        model.addAttribute("chapterEntity", chapterEntity);

        model.addAttribute("lesson", lessonEntity);
        List<ContentLessonEntity> contentLessons = lessonEntity.getContent_lessons();
        model.addAttribute("contentLessons", contentLessons);
        System.out.println(contentLessons);
        return "dashboard/lesson/edit";
    }
    @PostMapping("/admin/lesson/{lessonId}/update")
    public String updateLesson(@PathVariable("lessonId") Long lessonId, @RequestParam("chapterId") Long chapterId, @RequestParam("lessonName") String lessonName, @RequestParam("description") String description, @RequestParam("content") String content) {
        LessonEntity lessonEntity = lessonReponsitory.findById(lessonId).orElse(null);
        if (lessonEntity == null) {
            // Xử lý khi không tìm thấy bài học
            return "redirect:/admin/class/subject/ingredients/chapter/lesson";
        }

        lessonEntity.setLessonName(lessonName);
        lessonEntity.setDescription(description);
        ChapterEntity chapterEntity = chapterReponsitory.findById(chapterId).orElse(null);
        if (chapterEntity != null) {
            lessonEntity.setChapter_tbl(chapterEntity);
        }
        lessonReponsitory.save(lessonEntity);

        ContentLessonEntity contentLessonEntity = contentLessonReponsitory.findByLesson(lessonEntity);
        if (contentLessonEntity == null) {
            contentLessonEntity = new ContentLessonEntity();
            contentLessonEntity.setLesson_tbl(lessonEntity);
        }
        contentLessonEntity.setContents(content);
        contentLessonReponsitory.save(contentLessonEntity);

        return "redirect:/admin/class/subject/ingredients/chapter/lesson";
    }

    @GetMapping("/admin/lesson/{id}/delete")
    public String deleteLessonEntity(@PathVariable("id") Long id) {
        lessonReponsitory.deleteById(id);
        return "redirect:/admin/class/subject/ingredients/chapter/lesson";
    }
}
