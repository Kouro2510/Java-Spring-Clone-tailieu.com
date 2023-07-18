package com.example.bodethi.repository;

import com.example.bodethi.entity.ContentLessonEntity;
import com.example.bodethi.entity.ContentTopicEntity;
import com.example.bodethi.entity.LessonEntity;
import com.example.bodethi.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContentTopicReponsitory extends JpaRepository<ContentTopicEntity,Long> {

    @Query("SELECT c FROM ContentTopicEntity c WHERE c.topic_tbl = :topic")
    ContentTopicEntity findByTopic(@Param("topic") TopicEntity topic);
}
