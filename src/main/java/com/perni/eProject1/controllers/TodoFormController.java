package com.perni.eProject1.controllers;

import com.perni.eProject1.models.TodoEntity;
import com.perni.eProject1.services.TodoEntityService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id")Long id, Model model){
        TodoEntity todoEntity= todoEntityService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("TodoItem id: " + id + " not found"));
        todoEntityService.delete(todoEntity);
        return "redirect:/user";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){

        TodoEntity todoEntity= todoEntityService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("TodoItem id: " + id + " not found"));
        model.addAttribute("todo", todoEntity);
        return "edit-todo-item";
    }
    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoEntity todoEntity, BindingResult result, Model model){
        System.out.println("Complete attribute received from form: " + todoEntity.getComplete());
        TodoEntity item = todoEntityService
                .getById(id)
                .orElseThrow(()-> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setComplete(todoEntity.getComplete());
        item.setDescription(todoEntity.getDescription());

        todoEntityService.save(item);


        return "redirect:/user";

    }

}
