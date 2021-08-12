package se.lexicon.g36todoit.model.dto;

import se.lexicon.g36todoit.model.entity.TodoItem;

import java.io.Serializable;
import java.util.List;

public class PersonDTO implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private List<TodoItem> assignedTodos;

    public PersonDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<TodoItem> getAssignedTodos() {
        return assignedTodos;
    }

    public void setAssignedTodos(List<TodoItem> assignedTodos) {
        this.assignedTodos = assignedTodos;
    }
}
