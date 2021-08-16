package se.lexicon.g36todoit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g36todoit.model.entity.AppUser;
import se.lexicon.g36todoit.model.entity.AppUserRole;

import java.util.List;
import java.util.Optional;

public interface AppUserDAO extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    @Query("SELECT user FROM AppUser user WHERE user.role = :role")
    List<AppUser> findByRole(@Param("role") AppUserRole role);

}
