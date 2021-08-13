package se.lexicon.g36todoit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.lexicon.g36todoit.model.entity.TodoItem;

import java.util.List;

public interface TodoItemDAO extends JpaRepository<TodoItem, Integer> {

    @Query("SELECT i FROM TodoItem i WHERE i.assignee IS null AND i.deadLine > current_date AND i.done = false")
    List<TodoItem> findAvailableTodoItems();

    List<TodoItem> findByDone(boolean done);

    @Query("SELECT i FROM TodoItem i WHERE i.done = false  AND i.deadLine < current_date")
    List<TodoItem> findAllExpiredAndIncompleteTodoItems();


}
