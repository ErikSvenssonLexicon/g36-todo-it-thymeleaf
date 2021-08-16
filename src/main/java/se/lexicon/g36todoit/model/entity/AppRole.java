package se.lexicon.g36todoit.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "app_role")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_role_id")
    private Integer id;
    @Column(unique = true)
    private AppUserRole role;

    public AppRole(Integer id, AppUserRole role) {
        this.id = id;
        this.role = role;
    }

    public AppRole(AppUserRole role) {
        this(null, role);
    }

    public AppRole() {
    }

    public Integer getId() {
        return id;
    }

    public AppUserRole getRole() {
        return role;
    }

    public void setRole(AppUserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return getRole() == appRole.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole());
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
