package se.lexicon.g36todoit.service;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g36todoit.model.dto.TodoItemDTO;
import se.lexicon.g36todoit.model.entity.TodoItem;

import java.util.List;

public interface TodoItemService {
    @Transactional(rollbackFor = RuntimeException.class)
    TodoItem create(TodoItemDTO dto);

    @Transactional(readOnly = true)
    TodoItem findById(Integer id);

    @Transactional(readOnly = true)
    List<TodoItem> findAll();

    @Transactional(rollbackFor = RuntimeException.class)
    TodoItem update(Integer id, TodoItemDTO dto);

    @Transactional(rollbackFor = RuntimeException.class)
    boolean delete(Integer id);
}
