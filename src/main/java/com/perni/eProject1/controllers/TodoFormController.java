package com.perni.eProject1.controllers;

import com.perni.eProject1.models.TodoEntity;
import com.perni.eProject1.services.TodoEntityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoFormController {
    final TodoEntityService todoEntityService;

    public TodoFormController(TodoEntityService todoEntityService) {
        this.todoEntityService = todoEntityService;
    }
    @GetMapping("/create-todo")
    public String showCreateForm(TodoEntity todoEntity){
        return "create-todo";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoEntity todoEntity, BindingResult result, Model model){
        TodoEntity item = new TodoEntity();
        item.setDescription(todoEntity.getDescription());
        item.setComplete(todoEntity.getComplete());

        todoEntityService.save(todoEntity);
        return "redirect:/user";

    }

}
