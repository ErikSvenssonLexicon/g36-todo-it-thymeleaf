package se.lexicon.g36todoit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g36todoit.dao.AppUserDAO;
import se.lexicon.g36todoit.model.dto.AppUserDTO;
import se.lexicon.g36todoit.model.dto.PersonDTO;
import se.lexicon.g36todoit.model.entity.AppUserRole;
import se.lexicon.g36todoit.model.entity.Person;
import se.lexicon.g36todoit.service.PersonService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final AppUserDAO appUserDAO;

    public PersonController(PersonService personService, AppUserDAO appUserDAO) {
        this.personService = personService;
        this.appUserDAO = appUserDAO;
    }

    @GetMapping()
    public String searchPeople(
            @RequestParam(name = "type", defaultValue = "all") String type,
            @RequestParam(name = "value", defaultValue = "") String value,
            Model model){

        List<Person> people = new ArrayList<>();

        switch (type){
            case "all":
                people = personService.findAll();
                break;
            case "name":
                people = personService.findByNameContains(value);
                break;
            default:
                model.addAttribute("error", "Invalid type, valid types are 'all' and 'name'");
        }

        if(people.isEmpty() && type.equals("name")){
            model.addAttribute("notFound", "No people were found with search: " + value);
        }

        model.addAttribute("people", people);
        return "people-dashboard";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAssignedTodos(new ArrayList<>());
        personDTO.setAppUserForm(new AppUserDTO());
        model.addAttribute("form", personDTO);
        model.addAttribute("actionUrl", "/people/create/process");
        return "person-form";
    }

    @PostMapping("/create/process")
    public String processCreate(@Valid @ModelAttribute(name = "form") PersonDTO form, BindingResult bindingResult){

        AppUserDTO appUserForm = form.getAppUserForm();
        if(appUserDAO.findByUsername(appUserForm.getUsername().trim()).isPresent()){
            FieldError error = new FieldError("form", "appUserForm.username", "Username " + appUserForm.getUsername().trim() + " is already taken");
            bindingResult.addError(error);
        }

        if(!appUserForm.getPassword().equals(appUserForm.getPasswordConfirm())){
            FieldError error = new FieldError("form", "appUserForm.passwordConfirm", "Input did not match password");
            bindingResult.addError(error);
        }

        if(bindingResult.hasErrors()){
            return "person-form";
        }
        Person saved = personService.create(form, AppUserRole.APP_USER);

        return "redirect:/public/login";
    }

    @GetMapping("/{username}")
    public String findById(@PathVariable("username") String username, Model model){
        model.addAttribute("person", personService.findByUsername(username));
        return "person-view";
    }

    @GetMapping("/{username}/update")
    public String getUpdateForm(@PathVariable("username") String username, Model model){
        Person person = personService.findByUsername(username);
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        model.addAttribute("form", personDTO);
        model.addAttribute("actionUrl", "/people/"+username+"/update/process");
        return "person-form";
    }

    @PostMapping("/{username}/update/process")
    public String processUpdate(@PathVariable("username") String username, @Valid @ModelAttribute("form") PersonDTO form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "person-form";
        }
        personService.update(username, form);
        return "redirect:/people/"+username;
    }

}
