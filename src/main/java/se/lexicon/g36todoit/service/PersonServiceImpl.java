package se.lexicon.g36todoit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.dao.PersonDAO;
import se.lexicon.g36todoit.model.dto.PersonDTO;
import se.lexicon.g36todoit.model.entity.Person;
import se.lexicon.g36todoit.model.entity.TodoItem;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDAO personDAO;

    public PersonServiceImpl(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Person create(PersonDTO dto){
        Person newPerson = new Person(
                dto.getFirstName().trim(),
                dto.getLastName().trim()
        );
        return personDAO.save(newPerson);
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Integer id){
        return personDAO.findById(id)
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll(){
        return personDAO.findAll();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Person update(Integer id, PersonDTO dto){
        Person original = findById(id);
        original.setFirstName(dto.getFirstName().trim());
        original.setLastName(dto.getLastName().trim());
        return personDAO.save(original);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(Integer id){
        Person person = findById(id);
        for(TodoItem todoItem : person.getAssignedTodos()){
            todoItem.setAssignee(null);
        }
        person.setAssignedTodos(null);
        personDAO.delete(person);
        return !personDAO.existsById(id);
    }
}
