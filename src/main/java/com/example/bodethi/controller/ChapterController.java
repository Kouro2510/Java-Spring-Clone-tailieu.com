package com.example.bodethi.controller;

import com.example.bodethi.entity.ChapterEntity;
import com.example.bodethi.entity.ClassEntity;
import com.example.bodethi.entity.IngredientsEntity;
import com.example.bodethi.entity.SubjectEntity;
import com.example.bodethi.repository.ChapterReponsitory;
import com.example.bodethi.repository.ClassReponsitory;
import com.example.bodethi.repository.IngredientsReponsitory;
import com.example.bodethi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ChapterController {
    @Autowired
    private ClassReponsitory classRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private IngredientsReponsitory ingredientsReponsitory;
    @Autowired
    private ChapterReponsitory chapterReponsitory;
    @GetMapping("/admin/class/subject/ingredients/chapter")
    public String GetAllChapter(Model model){
        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<SubjectEntity> SubjectEntity = subjectRepository.findAll();
        model.addAttribute("SubjectEntity", SubjectEntity);
        List<IngredientsEntity> ingredientsEntity = ingredientsReponsitory.findAll();
        model.addAttribute("ingredientsEntity", ingredientsEntity);
        List<ChapterEntity> chapterEntity = chapterReponsitory.findAll();
        model.addAttribute("chapterEntity", chapterEntity);
        return "dashboard/chapter/index";
    }
    @GetMapping("/admin/ingredients/{ingredientsId}/chapter")
    public String getChapterByIngredientsId(Model model, @PathVariable("ingredientsId") Long ingredientsId) {

        List<ClassEntity> classEntities = classRepository.findAll();
        model.addAttribute("classEntities", classEntities);
        List<SubjectEntity> SubjectEntity = subjectRepository.findAll();
        model.addAttribute("SubjectEntity", SubjectEntity);
        List<IngredientsEntity> IngredientsEntity = ingredientsReponsitory.findAll();
        model.addAttribute("ingredients", IngredientsEntity);
        model.addAttribute("ingredientsId",ingredientsId);
        IngredientsEntity ingredientsEntity = ingredientsReponsitory.findById(ingredientsId).orElse(null);
        if (ingredientsEntity != null) {
            List<ChapterEntity> chapter = ingredientsEntity.getChapters(); // Lấy danh sách môn học từ lớp học
            model.addAttribute("chapter", chapter);
        }

        return "dashboard/chapter/FindbyIngredients";
    }
    @PostMapping("/admin/classes/chapter/save")
    public String SaveChapterByIngredients(@RequestParam("ingredientsId") Long ingredientsId, @RequestParam("chapterName") String chapterName, @RequestParam("description") String description) {
        ChapterEntity  chapterEntity = new ChapterEntity();
        chapterEntity.setChapterName(chapterName);
        chapterEntity.setDescription(description);
        IngredientsEntity IngredientsEntity = ingredientsReponsitory.findById(ingredientsId).orElse(null);
        if (IngredientsEntity != null) {
            chapterEntity.setIngredients_tbl(IngredientsEntity);
            chapterReponsitory.save(chapterEntity);
            return "redirect:/admin/class/subject/ingredients/chapter";
        } else {
            return "dashboard/ingredients/index";
        }
    }
    @PostMapping("/admin/classes/chapterid/save")
    public String SaveChapterByIngredientsbyid(@RequestParam("ingredientsId") Long ingredientsId, @RequestParam("chapterName") String chapterName, @RequestParam("description") String description) {
        ChapterEntity  chapterEntity = new ChapterEntity();
        chapterEntity.setChapterName(chapterName);
        chapterEntity.setDescription(description);
        IngredientsEntity IngredientsEntity = ingredientsReponsitory.findById(ingredientsId).orElse(null);
        if (IngredientsEntity != null) {
            chapterEntity.setIngredients_tbl(IngredientsEntity);
            chapterReponsitory.save(chapterEntity);
            return "redirect:/admin/ingredients/" + ingredientsId + "/chapter";
        } else {
            return "dashboard/ingredients/index";
        }
    }
    @PostMapping("/admin/chapter/{id}/update")
    public String updateChapterEntity(@PathVariable("id") Long id, @RequestParam("ingredientsId") Long ingredientsId, @ModelAttribute ChapterEntity  updatedChapterEntity) {
        Optional<ChapterEntity> ChapterEntityOptional =  chapterReponsitory.findById(id);
        if (ChapterEntityOptional.isPresent()) {
            ChapterEntity ChapterEntity = ChapterEntityOptional.get();
            ChapterEntity.setChapterName(updatedChapterEntity.getChapterName());
            ChapterEntity.setDescription(updatedChapterEntity.getDescription());
            IngredientsEntity ingredientsEntity = ingredientsReponsitory.findById(ingredientsId).orElse(null);
            if (ingredientsEntity != null) {
                ChapterEntity.setIngredients_tbl(ingredientsEntity);
            }
            chapterReponsitory.save(ChapterEntity);
        }
        return "redirect:/admin/class/subject/ingredients/chapter";
    }
    @PostMapping("/admin/chapter-{id}/update")
    public String updateChapterEntityIngredientsbyid(@PathVariable("id") Long id, @RequestParam("ingredientsId") Long ingredientsId, @ModelAttribute ChapterEntity  updatedChapterEntity) {
        Optional<ChapterEntity> ChapterEntityOptional =  chapterReponsitory.findById(id);
        if (ChapterEntityOptional.isPresent()) {
            ChapterEntity ChapterEntity = ChapterEntityOptional.get();
            ChapterEntity.setChapterName(updatedChapterEntity.getChapterName());
            ChapterEntity.setDescription(updatedChapterEntity.getDescription());
            IngredientsEntity ingredientsEntity = ingredientsReponsitory.findById(ingredientsId).orElse(null);
            if (ingredientsEntity != null) {
                ChapterEntity.setIngredients_tbl(ingredientsEntity);
            }
            chapterReponsitory.save(ChapterEntity);
        }
        return "redirect:/admin/ingredients/" + ingredientsId + "/chapter";
    }
    @GetMapping("/admin/chapter/{id}/delete")
    public String deleteChapterEntity(@PathVariable("id") Long id) {
        chapterReponsitory.deleteById(id);
        return "redirect:/admin/class/subject/ingredients/chapter";
    }
    @GetMapping("/admin/chapter-{id}/delete")
    public String deleteChapterEntitybyiD(@RequestParam("ingredientsId") Long ingredientsId,@PathVariable("id") Long id) {
        chapterReponsitory.deleteById(id);
        return "redirect:/admin/ingredients/" + ingredientsId + "/chapter";
    }
}
