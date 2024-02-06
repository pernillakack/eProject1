package com.perni.eProject1.services;

import com.perni.eProject1.models.TodoEntity;
import com.perni.eProject1.repositories.TodoItemRepository;
import com.perni.eProject1.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TodoEntityService {

    final TodoItemRepository todoItemRepository;
    final UserRepository userRepository;

    public TodoEntityService(TodoItemRepository todoItemRepository, UserRepository userRepository) {
        this.todoItemRepository = todoItemRepository;
        this.userRepository = userRepository;
    }

    public Iterable<TodoEntity>getAll(){
        return todoItemRepository.findAll();
    }
    public Optional<TodoEntity>getById(Long id){
        return todoItemRepository.findById(id);
    }

    public TodoEntity save (TodoEntity todoEntity){
        if(todoEntity.getId() == null){
            todoEntity.setCreatedAt(Instant.now());
        }
        todoEntity.setUpdatedAt(Instant.now());
        todoEntity.setComplete(false);
        return todoItemRepository.save(todoEntity);
    }

    public void delete(TodoEntity todoEntity){
        todoItemRepository.delete(todoEntity);
    }
}
