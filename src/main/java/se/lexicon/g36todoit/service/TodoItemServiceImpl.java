package se.lexicon.g36todoit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.dao.PersonDAO;
import se.lexicon.g36todoit.dao.TodoItemDAO;
import se.lexicon.g36todoit.model.dto.TodoItemDTO;
import se.lexicon.g36todoit.model.entity.Person;
import se.lexicon.g36todoit.model.entity.TodoItem;

import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService{

    private final TodoItemDAO todoItemDAO;
    private final PersonDAO personDAO;

    public TodoItemServiceImpl(TodoItemDAO todoItemDAO, PersonDAO personDAO) {
        this.todoItemDAO = todoItemDAO;
        this.personDAO = personDAO;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public TodoItem create(TodoItemDTO dto){
        TodoItem todoItem = new TodoItem(
                dto.getTitle(),
                dto.getDescription(),
                dto.getDeadLine()
        );

        if(dto.getAssignee() != null && dto.getId() != null){
            Person person = personDAO.findById(dto.getId()).orElseThrow();
            todoItem.setAssignee(person);
            person.getAssignedTodos().add(todoItem);
        }

        return todoItemDAO.save(todoItem);
    }

    @Override
    @Transactional(readOnly = true)
    public TodoItem findById(Integer id){
        return todoItemDAO.findById(id)
                .orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItem> findAll(){
        return todoItemDAO.findAll();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public TodoItem update(Integer id, TodoItemDTO dto){
        TodoItem todoItem = findById(id);
        todoItem.setTitle(dto.getTitle().trim());
        todoItem.setDescription(dto.getDescription());
        todoItem.setDeadLine(dto.getDeadLine());
        return todoItemDAO.save(todoItem);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(Integer id){
        TodoItem todoItem = findById(id);
        if(todoItem.getAssignee() != null){
            todoItem.getAssignee().getAssignedTodos().remove(todoItem);
            todoItem.setAssignee(null);
        }

        todoItemDAO.delete(todoItem);

        return !todoItemDAO.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItem> findAvailableTodoItems() {
        return todoItemDAO.findAvailableTodoItems();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItem> findByDone(boolean done) {
        return todoItemDAO.findByDone(done);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItem> findAllExpiredAndIncompleteTodoItems() {
        return todoItemDAO.findAllExpiredAndIncompleteTodoItems();
    }

}
