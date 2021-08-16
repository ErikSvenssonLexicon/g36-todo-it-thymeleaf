package se.lexicon.g36todoit.model.dto;

import se.lexicon.g36todoit.model.entity.TodoItem;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class PersonDTO implements Serializable {

    private Integer id;

    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 255, message = "First name need to have at least two letters")
    private String firstName;

    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 255, message = "Last name need to have at least two letters")
    private String lastName;

    @Valid
    private AppUserDTO appUserForm;

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

    public AppUserDTO getAppUserForm() {
        return appUserForm;
    }

    public void setAppUserForm(AppUserDTO appUserForm) {
        this.appUserForm = appUserForm;
    }

    public List<TodoItem> getAssignedTodos() {
        return assignedTodos;
    }

    public void setAssignedTodos(List<TodoItem> assignedTodos) {
        this.assignedTodos = assignedTodos;
    }
}
