package com.example.bodethi.controller;

import com.example.bodethi.entity.*;
import com.example.bodethi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TopicController {
    @Autowired
    private ClassReponsitory classRepository;
    @Autowired
    private IngredientsReponsitory ingredientsReponsitory;
    @Autowired
    private TopicReponsitory topicReponsitory;
    @Autowired
    private ContentTopicReponsitory contentTopicReponsitory;
    @GetMapping("/admin/class/subject/ingredients/topic")
    public String getAllTopic(Model model){
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<IngredientsEntity> ingredientsEntities = ingredientsReponsitory.findAll();
        model.addAttribute("ingredientsEntities", ingredientsEntities);
        List<TopicEntity> topicEntities = topicReponsitory.findAll();
        model.addAttribute("topicEntities", topicEntities);
        return "dashboard/topic/index";
    }
    @GetMapping("/admin/class/subject/ingredients/topic/new")
    public String CreateNewLesson(Model model){
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<IngredientsEntity> ingredientsEntities = ingredientsReponsitory.findAll();
        model.addAttribute("ingredientsEntities", ingredientsEntities);
        return "dashboard/topic/new";
    }
    @PostMapping("/admin/topic/save")
    public String SaveNewLesson(@RequestParam("chapterId") Long chapterId, @RequestParam("topicName") String topicName, @RequestParam("description") String description, @RequestParam("content") String content){
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTopicName(topicName);
        topicEntity.setDescription(description);
        IngredientsEntity ingredientsEntities=ingredientsReponsitory.findById(chapterId).orElse(null);
        if (ingredientsEntities != null){
            topicEntity.setIngredients_tbl(ingredientsEntities);
        }
        topicReponsitory.save(topicEntity);
        ContentTopicEntity contentTopicEntity = new ContentTopicEntity();
        contentTopicEntity.setContents(content);
        contentTopicEntity.setTopic_tbl(topicEntity);
        contentTopicReponsitory.save(contentTopicEntity);

        return "redirect:/admin/class/subject/ingredients/topic";
    }
    @GetMapping("/admin/topic/{topicId}/edit")
    public String editTopic(@PathVariable Long topicId, Model model) {
        TopicEntity topicEntity = topicReponsitory.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson ID"));
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);

        List<IngredientsEntity> ingredientsEntities = ingredientsReponsitory.findAll();
        model.addAttribute("ingredientsEntities", ingredientsEntities);

        model.addAttribute("topic", topicEntity);
        List<ContentTopicEntity> contentTopics = topicEntity.getContent_topics();
        model.addAttribute("contentTopics", contentTopics);
        System.out.println(contentTopics);
        return "dashboard/topic/edit";
    }
    @PostMapping("/admin/topic/{topicId}/update")
    public String updateLesson(@PathVariable("topicId") Long topicId, @RequestParam("ingredientsId") Long ingredientsId, @RequestParam("topicName") String topicName, @RequestParam("description") String description, @RequestParam("content") String content) {
        TopicEntity topicEntity = topicReponsitory.findById(topicId).orElse(null);
        if (topicEntity == null) {
            // Xử lý khi không tìm thấy bài học
            return "redirect:/admin/class/subject/ingredients/topic";
        }

        topicEntity.setTopicName(topicName);
        topicEntity.setDescription(description);
        IngredientsEntity ingredientsEntity = ingredientsReponsitory.findById(ingredientsId).orElse(null);
        if (ingredientsEntity != null) {
            topicEntity.setIngredients_tbl(ingredientsEntity);
        }
        topicReponsitory.save(topicEntity);

        ContentTopicEntity contentTopicEntity = contentTopicReponsitory.findByTopic(topicEntity);
        if (contentTopicEntity == null) {
            ContentTopicEntity contentTopic = new ContentTopicEntity();
            contentTopic.setTopic_tbl(topicEntity);
        }
        contentTopicEntity.setContents(content);
        contentTopicReponsitory.save(contentTopicEntity);

        return "redirect:/admin/class/subject/ingredients/topic";
    }

    @GetMapping("/admin/topic/{id}/delete")
    public String deleteLessonEntity(@PathVariable("id") Long id) {
        topicReponsitory.deleteById(id);
        return "redirect:/admin/class/subject/ingredients/topic";
    }
}
