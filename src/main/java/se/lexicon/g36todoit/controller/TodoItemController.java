package se.lexicon.g36todoit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g36todoit.model.dto.TodoItemDTO;
import se.lexicon.g36todoit.model.entity.TodoItem;
import se.lexicon.g36todoit.service.TodoItemService;

import javax.validation.Valid;

@Controller
@RequestMapping("/todos")
public class TodoItemController {

    private final TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("form", new TodoItemDTO());
        model.addAttribute("actionUrl", "/todos/create/process");
        return "todo-item-form";
    }

    @PostMapping("/create/process")
    public String process(@Valid @ModelAttribute(name = "form") TodoItemDTO form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo-item-form";
        }

        TodoItem newTodoItem = todoItemService.create(form);

        return "redirect:/todos/"+newTodoItem.getId();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        model.addAttribute("todoItem", todoItemService.findById(id));
        return "todo-item-view";
    }


}
