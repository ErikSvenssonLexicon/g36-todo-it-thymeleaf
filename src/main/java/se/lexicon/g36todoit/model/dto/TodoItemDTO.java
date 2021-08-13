package se.lexicon.g36todoit.model.dto;

import se.lexicon.g36todoit.model.entity.Person;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class TodoItemDTO implements Serializable {
    private Integer id;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 50, message = "Title need to be between 2 and 50 letters long")
    private String title;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 1000, message = "Description need to have at least 2 letters")
    private String description;
    @NotNull(message = "This field is mandatory")
    @FutureOrPresent(message = "Dead line need to be in present or future")
    private LocalDate deadLine;
    private boolean done;
    private Person assignee;

    public TodoItemDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }
}
