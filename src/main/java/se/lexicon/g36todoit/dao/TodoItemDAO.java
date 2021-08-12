package se.lexicon.g36todoit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g36todoit.model.entity.TodoItem;

public interface TodoItemDAO extends JpaRepository<TodoItem, Integer> {
}
