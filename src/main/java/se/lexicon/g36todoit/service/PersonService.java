package se.lexicon.g36todoit.service;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.model.dto.PersonDTO;
import se.lexicon.g36todoit.model.entity.Person;

import java.util.List;

public interface PersonService {
    @Transactional(rollbackFor = RuntimeException.class)
    Person create(PersonDTO dto);

    @Transactional(readOnly = true)
    Person findById(Integer id);

    @Transactional(readOnly = true)
    List<Person> findAll();

    @Transactional(rollbackFor = RuntimeException.class)
    Person update(Integer id, PersonDTO dto);

    @Transactional(rollbackFor = RuntimeException.class)
    boolean delete(Integer id);
}
