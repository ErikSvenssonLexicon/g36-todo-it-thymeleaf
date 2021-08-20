package se.lexicon.g36todoit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g36todoit.model.dto.TodoItemDTO;
import se.lexicon.g36todoit.model.entity.TodoItem;
import se.lexicon.g36todoit.service.TodoItemService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoItemController {

    private final TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @GetMapping
    public String todos(@RequestParam(name = "search", defaultValue = "all") String search, Model model){

        List<TodoItem> todoItems = new ArrayList<>();

        switch (search){
            case "all":
                todoItems = todoItemService.findAll();
                break;
            case "available":
                todoItems = todoItemService.findAvailableTodoItems();
                break;
            case "completed":
                todoItems = todoItemService.findByDone(true);
                break;
            case "unfinished":
                todoItems = todoItemService.findByDone(false);
                break;
            case "expired_and_incomplete":
                todoItems = todoItemService.findAllExpiredAndIncompleteTodoItems();
                break;
            default:
                model.addAttribute("error", "" +
                        "Invalid search type: " + search+
                        " valid searches are " +
                        "'all', 'available', 'completed', 'unfinished' and 'expired_and_incomplete'");
                break;
        }
        model.addAttribute("todoItems", todoItems);
        return "todos_dashboard";

    }

    @GetMapping("/create")
    public String create(@RequestParam(name = "username") String username, @RequestParam(name = "self") boolean selfAssigned, Model model){
        model.addAttribute("form", new TodoItemDTO());
        if(selfAssigned){
            model.addAttribute("actionUrl", "/todos/create/process?username="+username+"&selfAssigned=true");
        }else{
            model.addAttribute("actionUrl", "/todos/create/process?username="+username+"&selfAssigned=false");
        }

        return "todo-item-form";
    }

    @PostMapping("/create/process")
    public String process(
            @Valid @ModelAttribute(name = "form") TodoItemDTO form,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "selfAssigned") boolean selfAssigned,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "todo-item-form";
        }

        TodoItem newTodoItem = todoItemService.create(username, selfAssigned, form);

        return "redirect:/todos/"+newTodoItem.getId();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        model.addAttribute("todoItem", todoItemService.findById(id));
        return "todo-item-view";
    }

    @GetMapping("/{id}/update")
    public String getUpdateForm(@PathVariable(name = "id") Integer id, Model model){
        TodoItem todoItem = todoItemService.findById(id);
        TodoItemDTO todoItemDTO = new TodoItemDTO();
        todoItemDTO.setId(todoItem.getId());
        todoItemDTO.setTitle(todoItem.getTitle());
        todoItemDTO.setDeadLine(todoItem.getDeadLine());
        todoItemDTO.setDescription(todoItem.getDescription());
        model.addAttribute("form", todoItemDTO);
        model.addAttribute("urlAction", "/todos/"+todoItem.getId()+"/update/process");
        return "todo-item-form";
    }

    @PostMapping("/{id}/update/process")
    public String processTodoItemUpdate(@PathVariable(name = "id") Integer id, @Valid @ModelAttribute(name = "form") TodoItemDTO form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo-item-form";
        }
        todoItemService.update(id, form);

        return "redirect:/todos/"+id;
    }

    @GetMapping("/{id}/complete")
    public String completeTodoItem(@PathVariable("id") Integer id){
        todoItemService.complete(id);
        return "redirect:/todos/"+id;
    }


}
