package com.example.bodethi.repository;

import com.example.bodethi.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassReponsitory extends JpaRepository<ClassEntity,Long> {
    @Query("SELECT c.classname FROM ClassEntity c WHERE c.id = :classId")
    String findClassNameById(@Param("classId") Long classId);

    ClassEntity findByUrl(String url);


}
