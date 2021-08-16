package se.lexicon.g36todoit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.g36todoit.model.entity.AppRole;

public interface AppRoleDAO extends JpaRepository<AppRole, Integer> {
}
