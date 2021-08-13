package se.lexicon.g36todoit.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "todo_item")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Integer id;
    @Column(name = "title", length = 50)
    private String title;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "deadline")
    private LocalDate deadLine;
    @Column(name = "done")
    private boolean done;
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "assignee_id")
    private Person assignee;

    public TodoItem(Integer id, String title, String description, LocalDate deadLine, boolean done, Person assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assignee = assignee;
    }

    public TodoItem(String title, String description, LocalDate deadLine) {
        this(null, title, description, deadLine, false, null);
    }

    public TodoItem() {
    }

    public Integer getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return isDone() == todoItem.isDone() && Objects.equals(getId(), todoItem.getId()) && Objects.equals(getTitle(), todoItem.getTitle()) && Objects.equals(getDescription(), todoItem.getDescription()) && Objects.equals(getDeadLine(), todoItem.getDeadLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getDeadLine(), isDone());
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }
}
