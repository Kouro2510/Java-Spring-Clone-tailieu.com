package com.example.bodethi.controller;

import com.example.bodethi.dto.ClassSubjectDTO;
import com.example.bodethi.dto.SubjectIngredients;
import com.example.bodethi.entity.*;
import com.example.bodethi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {
    @Autowired
    private ClassReponsitory classReponsitory;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private IngredientsReponsitory ingredientsReponsitory;
    @Autowired
    private TopicReponsitory topicReponsitory;
    @Autowired
    private ChapterReponsitory chapterReponsitory;
    @Autowired
    private LessonReponsitory lessonReponsitory;

    @GetMapping("/")
    public String homePage(Model model) {
        List<ClassEntity> classEntities = classReponsitory.findAll();
        model.addAttribute("classes", classEntities);
        List<ClassSubjectDTO> classSubjectDTOs = new ArrayList<>();
        for (ClassEntity classEntity : classEntities) {
            Integer classId = classEntity.getId();
            List<SubjectEntity> subjects = subjectRepository.findFirst4SubjectsByClassId(classId);
            List<SubjectEntity> limitedSubjects = subjects.subList(0, Math.min(subjects.size(), 5));
            classSubjectDTOs.add(new ClassSubjectDTO(classEntity, limitedSubjects));
        }
        model.addAttribute("classSubjectDTOs", classSubjectDTOs);
        return "page/homepage";
    }

    @GetMapping("/{urlsClass}")
    public String classDetail(@PathVariable("urlsClass") String url, Model model) {
        ClassEntity classEntitys = classReponsitory.findByUrl(url);
        System.out.println(url);
        if (classEntitys != null) {
            Long classId = Long.valueOf(classEntitys.getId());
            List<ClassEntity> classEntities = classReponsitory.findAll();
            ClassEntity classEntity = classReponsitory.findById(classId).orElse(null);
            if (classEntity != null) {
                String className = classEntity.getClassname();
                List<SubjectEntity> subjects = classEntity.getSubjects();
                List<SubjectIngredients> subjectIngredients = new ArrayList<>();
                List<TopicEntity> allTopics = new ArrayList<>();
                for (SubjectEntity subjectEntity : subjects) {
                    Integer subjectId = subjectEntity.getId();
                    List<IngredientsEntity> ingredientsEntities = ingredientsReponsitory.findFirst4IngredientsBySubjectID(subjectId);
                    List<Integer> ingredientIds = new ArrayList<>();
                    for (IngredientsEntity ingredientEntity : ingredientsEntities) {
                        ingredientIds.add(ingredientEntity.getId());
                    }
                    subjectIngredients.add(new SubjectIngredients(subjectEntity, ingredientsEntities, ingredientIds));
                    for (Integer ingredientId : ingredientIds) {
                        List<TopicEntity> ingredientTopics = topicReponsitory.findFirst4IngredientsByIngredientsID(ingredientId);
                        allTopics.addAll(ingredientTopics);
                    }
                }
                model.addAttribute("classes", classEntities);
                model.addAttribute("classId", classId);
                model.addAttribute("classEntity", classEntity);
                model.addAttribute("className", className);
                model.addAttribute("subjectIngredients", subjectIngredients);
                model.addAttribute("topics", allTopics);
            }
        }

        return "page/class1";
    }


    @GetMapping("/class-{classId}/{subjectName}/{ingredientsName}")
    public String ingredientsDetails(@PathVariable("classId") Long classId,
                                     @PathVariable("ingredientsName") String ingredientsName,
                                     @PathVariable("subjectName") String subjectName,
                                     Model model) {
        Optional<IngredientsEntity> optionalIngredient = ingredientsReponsitory.findByIngredientsName(ingredientsName);
        if (optionalIngredient.isPresent()) {
            IngredientsEntity ingredient = optionalIngredient.get();
            Integer ingredientsId = ingredient.getId();
            List<ClassEntity> classEntities = classReponsitory.findAll();
            List<ChapterEntity> chapterEntities = chapterReponsitory.finfbyIngredientsId(ingredientsId);
            for (ChapterEntity chapterEntity : chapterEntities) {
                Integer chapterId = chapterEntity.getId();
                List<LessonEntity> lessonEntities = lessonReponsitory.findBychapter_tbl(chapterId);
                chapterEntity.setLessonEntities(lessonEntities);
            }
            String className = classReponsitory.findClassNameById(classId);
            List<TopicEntity> topicEntities = topicReponsitory.findFirst4IngredientsByIngredientsID(ingredientsId);

            model.addAttribute("chapterEntities", chapterEntities);
            model.addAttribute("ingredientsId", ingredientsId);
            model.addAttribute("ingredientsName", ingredientsName);
            model.addAttribute("subjectName", subjectName);
            model.addAttribute("classId", classId);
            model.addAttribute("classes", classEntities);
            model.addAttribute("className", className);
            model.addAttribute("topicEntities", topicEntities);

            return "page/class111";
        } else {
            return "page/class1";
        }
    }

    @GetMapping("/class-{classId}/{subjectName}/{ingredientsName}/{lessonName}")
    public String lessonDetails(@PathVariable("classId") Long classId,
                                @PathVariable("subjectName") String subjectName,
                                @PathVariable("ingredientsName") String ingredientsName,
                                @PathVariable("lessonName") String lessonName,
                                Model model) {

        Optional<IngredientsEntity> optionalIngredient = ingredientsReponsitory.findByIngredientsName(ingredientsName);
        Optional<LessonEntity> optionalLesson = lessonReponsitory.findByLessonName(lessonName);
        LessonEntity lesson = optionalLesson.get();
        Long LessonId = Long.valueOf(lesson.getId());
        IngredientsEntity ingredient = optionalIngredient.get();
        Integer ingredientsId = ingredient.getId();
        List<ClassEntity> classEntities = classReponsitory.findAll();
        String className = classReponsitory.findClassNameById(classId);
        List<ChapterEntity> chapterEntities = chapterReponsitory.finfbyIngredientsId(ingredientsId);
        for (ChapterEntity chapterEntity : chapterEntities) {
            Integer chapterId = chapterEntity.getId();
            List<LessonEntity> lessonEntities = lessonReponsitory.findBychapter_tbl(chapterId);
            chapterEntity.setLessonEntities(lessonEntities);
        }
        List<TopicEntity> topicEntities=topicReponsitory.findFirst4IngredientsByIngredientsID(ingredientsId);
        List<TopicEntity> topic6size = topicEntities.subList(0, Math.min(topicEntities.size(), 6));
        model.addAttribute("topic6size", topic6size);
        model.addAttribute("topicEntities", topicEntities);
        model.addAttribute("chapterEntities", chapterEntities);
        model.addAttribute("classes", classEntities);
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("ingredientsName", ingredientsName);
        model.addAttribute("lessonName", lessonName);
        model.addAttribute("className", className);
        model.addAttribute("ingredientsId", ingredientsId);
        Optional<LessonEntity> lessonEntityOptional = lessonReponsitory.findById(LessonId);
        if (lessonEntityOptional.isPresent()) {
            LessonEntity lessonEntity = lessonEntityOptional.get();
            model.addAttribute("lessonEntity", lessonEntity);
            List<ContentLessonEntity> contentLessons = lessonEntity.getContent_lessons();
            model.addAttribute("contentLessons", contentLessons);
        } else {
            model.addAttribute("lessonEntity", null);
            model.addAttribute("contentLessons", new ArrayList<>());
        }

        return "page/class1111";
    }

    @GetMapping("/class-{classId}/{subjectName}/{ingredientsName}/topicName={topicName}")
    public String topicDetails(@PathVariable("classId") Long classId,
                                @PathVariable("subjectName") String subjectName,
                                @PathVariable("ingredientsName") String ingredientsName,
                                @PathVariable("topicName") String topicName,
                                Model model) {
        Optional<IngredientsEntity> optionalIngredient = ingredientsReponsitory.findByIngredientsName(ingredientsName);
        Optional<TopicEntity> optionalTopic = topicReponsitory.findByTopicName(topicName);
        TopicEntity topic = optionalTopic.get();
        Long TopicId = Long.valueOf(topic.getId());
        IngredientsEntity ingredient = optionalIngredient.get();
        Integer ingredientsId = ingredient.getId();
        List<ClassEntity> classEntities = classReponsitory.findAll();
        String className = classReponsitory.findClassNameById(classId);
        List<ChapterEntity> chapterEntities = chapterReponsitory.finfbyIngredientsId(ingredientsId);
        for (ChapterEntity chapterEntity : chapterEntities) {
            Integer chapterId = chapterEntity.getId();
            List<LessonEntity> lessonEntities = lessonReponsitory.findBychapter_tbl(chapterId);
            chapterEntity.setLessonEntities(lessonEntities);
        }
        List<TopicEntity> topicEntities=topicReponsitory.findFirst4IngredientsByIngredientsID(ingredientsId);
        List<TopicEntity> topic6size = topicEntities.subList(0, Math.min(topicEntities.size(), 6));
        model.addAttribute("topic6size", topic6size);
        model.addAttribute("topicEntities", topicEntities);
        model.addAttribute("chapterEntities", chapterEntities);
        model.addAttribute("classes", classEntities);
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("ingredientsName", ingredientsName);
        model.addAttribute("lessonName", topicName);
        model.addAttribute("className", className);
        model.addAttribute("ingredientsId", ingredientsId);
        Optional<TopicEntity> topicEntityOptional = topicReponsitory.findById(TopicId);
        if (topicEntityOptional.isPresent()) {
            TopicEntity topicEntity = topicEntityOptional.get();
            model.addAttribute("topicEntity", topicEntity);
            List<ContentTopicEntity> contentTopic = topicEntity.getContent_topics();
            model.addAttribute("contentTopic", contentTopic);
        } else {
            model.addAttribute("topicEntity", null);
            model.addAttribute("contentTopic", new ArrayList<>());
        }

        return "page/class11111";
    }
}