package com.example.bodethi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic_tbl")
public class TopicEntity extends BaseEntity{
    @NotNull
    @Column(name = "topic_name")
    private String topicName;

    @Column
    private String description;
    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "ingredients_id")
    private IngredientsEntity ingredients_tbl;

    @OneToMany(mappedBy = "topic_tbl", cascade = CascadeType.ALL)
    private List<ContentTopicEntity> content_topics = new ArrayList<>();

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IngredientsEntity getIngredients_tbl() {
        return ingredients_tbl;
    }

    public void setIngredients_tbl(IngredientsEntity ingredients_tbl) {
        this.ingredients_tbl = ingredients_tbl;
    }

    public List<ContentTopicEntity> getContent_topics() {
        return content_topics;
    }

    public void setContent_topics(List<ContentTopicEntity> content_topics) {
        this.content_topics = content_topics;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
