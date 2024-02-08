package com.perni.eProject1.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "todos")
public class TodoEntity implements Serializable {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private boolean isComplete;
    private Instant createdAt;
    private Instant updatedAt;

    public Long getId() {
        return id;
    }

    public boolean getComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return String.format("TodoItem{id='%d', description='%s', isComplete='%s', createdAt='%s', updatedAt='%s'}",
                id, description, isComplete, createdAt, updatedAt);
    }
}
