package se.lexicon.g36todoit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g36todoit.model.entity.AppRole;
import se.lexicon.g36todoit.model.entity.AppUserRole;

import java.util.Optional;

public interface AppRoleDAO extends JpaRepository<AppRole, Integer> {

    @Query("SELECT r FROM AppRole r WHERE r.role = :role")
    Optional<AppRole> findByAppUserRole(@Param("role")AppUserRole appUserRole);

}
