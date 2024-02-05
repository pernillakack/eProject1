package com.perni.eProject1.repositories;

import com.perni.eProject1.models.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoEntity,Long> {
}
