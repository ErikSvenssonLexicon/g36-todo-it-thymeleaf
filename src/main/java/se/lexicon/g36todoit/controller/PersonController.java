package se.lexicon.g36todoit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g36todoit.dao.PersonDAO;
import se.lexicon.g36todoit.model.dto.PersonDTO;
import se.lexicon.g36todoit.model.entity.Person;

import java.util.ArrayList;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/create")
    public String getCreateForm(Model model){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAssignedTodos(new ArrayList<>());
        model.addAttribute("form", personDTO);
        return "person-form";
    }

    @PostMapping("/create/process")
    public String processCreate(@ModelAttribute(name = "form") PersonDTO form){
        Person person = new Person(
                form.getFirstName().trim(),
                form.getLastName().trim()
        );

        Person saved = personDAO.save(person);
        return "redirect:/people/"+saved.getId();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model){
        Person person = personDAO.findById(id).orElseThrow();
        model.addAttribute("person", person);
        return "person-view";
    }

}
