package com.example.bodethi.repository;


import com.example.bodethi.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    @Query("SELECT s FROM SubjectEntity s, ClassEntity  c WHERE s.class_tbl.id = :classId and s.class_tbl.id = c.id")
    List<SubjectEntity> findFirst4SubjectsByClassId(@Param("classId") Integer  classId);
}