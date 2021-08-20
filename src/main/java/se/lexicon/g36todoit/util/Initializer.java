package se.lexicon.g36todoit.util;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.dao.AppRoleDAO;
import se.lexicon.g36todoit.model.dto.TodoItemDTO;
import se.lexicon.g36todoit.model.entity.AppRole;
import se.lexicon.g36todoit.model.entity.AppUserRole;
import se.lexicon.g36todoit.service.PersonService;
import se.lexicon.g36todoit.service.TodoItemService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class Initializer {

    private final AppRoleDAO appRoleDAO;
    private final PersonService personService;
    private final TodoItemService todoItemService;

    public Initializer(AppRoleDAO appRoleDAO, PersonService personService, TodoItemService todoItemService) {
        this.appRoleDAO = appRoleDAO;
        this.personService = personService;
        this.todoItemService = todoItemService;
    }

    /*
                This method runs after spring has finished dependency injection
             */
    @PostConstruct
    @Transactional
    public void initialize(){
        if(appRoleDAO.count() == 0){
            Arrays.stream(AppUserRole.values())     //Creating a Stream of AppUserRole enums
                    .map(AppRole::new)              //Converting AppUserRole to AppRole objects
                    .forEach(appRoleDAO::save);     //Saving each AppRole in the database
        }
    }

}
