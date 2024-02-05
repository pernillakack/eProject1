package com.perni.eProject1.controllers;

import com.perni.eProject1.services.TodoEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {

    final TodoEntityService todoEntityService;

    public TodoController(TodoEntityService todoEntityService) {
        this.todoEntityService = todoEntityService;
    }

    @GetMapping("/user")
    public ModelAndView userView(){
        ModelAndView modelAndView =new ModelAndView("user");
        modelAndView.addObject("todoEntity", todoEntityService.getAll());

        return modelAndView;
    }
}
