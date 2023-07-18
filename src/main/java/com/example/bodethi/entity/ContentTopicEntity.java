package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "content_topic_tbl")
public class ContentTopicEntity extends BaseEntity{
    @NotNull
    @Column(columnDefinition = "text")
    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic_tbl;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public TopicEntity getTopic_tbl() {
        return topic_tbl;
    }

    public void setTopic_tbl(TopicEntity topic_tbl) {
        this.topic_tbl = topic_tbl;
    }
}
