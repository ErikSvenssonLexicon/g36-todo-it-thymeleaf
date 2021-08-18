package se.lexicon.g36todoit.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "app_user_id", table = "person"
    )
    private AppUser userCredentials;
    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "assignee"
    )
    private List<TodoItem> assignedTodos;

    public Person(Integer id, String firstName, String lastName, List<TodoItem> assignedTodos) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assignedTodos = assignedTodos;
    }

    public Person(String firstName, String lastName) {
        this(null, firstName, lastName, new ArrayList<>());
    }

    public Person() {
    }

    public Integer getId() {
        return id;
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
        if(assignedTodos == null) assignedTodos = new ArrayList<>();
        return assignedTodos;
    }

    public void setAssignedTodos(List<TodoItem> assignedTodos) {
        this.assignedTodos = assignedTodos;
    }

    public AppUser getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(AppUser userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
