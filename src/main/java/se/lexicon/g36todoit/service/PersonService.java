package se.lexicon.g36todoit.service;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.model.dto.PersonDTO;
import se.lexicon.g36todoit.model.entity.AppUserRole;
import se.lexicon.g36todoit.model.entity.Person;

import java.util.List;

public interface PersonService {
    @Transactional(rollbackFor = RuntimeException.class)
    Person create(PersonDTO dto, AppUserRole role);

    @Transactional(readOnly = true)
    Person findById(Integer id);

    @Transactional(readOnly = true)
    List<Person> findAll();

    @Transactional(rollbackFor = RuntimeException.class)
    Person update(String username, PersonDTO dto);

    @Transactional(rollbackFor = RuntimeException.class)
    boolean delete(Integer id);

    @Transactional(rollbackFor = RuntimeException.class)
    Person assignTodoItem(String username, Integer todoItemId);

    @Transactional(rollbackFor = RuntimeException.class)
    Person removeTodoItem(String username, Integer todoItemId);

    List<Person> findByNameContains(String search);

    Person findByUsername(String username);
}
