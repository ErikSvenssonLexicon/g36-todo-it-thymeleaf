package se.lexicon.g36todoit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g36todoit.dao.PersonDAO;
import se.lexicon.g36todoit.model.dto.PersonDTO;
import se.lexicon.g36todoit.model.entity.Person;

import javax.validation.Valid;
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
        model.addAttribute("actionUrl", "/people/create/process");
        return "person-form";
    }

    @PostMapping("/create/process")
    public String processCreate(@Valid @ModelAttribute(name = "form") PersonDTO form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "person-form";
        }

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

    @GetMapping("/{id}/update")
    public String getUpdateForm(@PathVariable("id") Integer id, Model model){
        Person person = personDAO.findById(id).orElseThrow();
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        model.addAttribute("form", personDTO);
        model.addAttribute("actionUrl", "/people/"+id+"/update/process");
        return "person-form";
    }

    @PostMapping("/{id}/update/process")
    public String processUpdate(@PathVariable("id") Integer id, @Valid @ModelAttribute("form") PersonDTO form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "person-form";
        }

        Person toUpdate = personDAO.findById(id).orElseThrow();

        toUpdate.setFirstName(form.getFirstName().trim());
        toUpdate.setLastName(form.getLastName().trim());

        personDAO.save(toUpdate);

        return "redirect:/people/"+id;
    }

}
