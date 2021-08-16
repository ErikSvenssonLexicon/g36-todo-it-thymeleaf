package se.lexicon.g36todoit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.g36todoit.model.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE UPPER(p.firstName) LIKE UPPER(CONCAT('%', :search, '%')) OR UPPER(p.lastName) LIKE UPPER(CONCAT('%', :search, '%'))")
    List<Person> findByNameContains(@Param("search") String searchData);
    @Query("SELECT p FROM Person p WHERE p.userCredentials.username = :username")
    Optional<Person> findByUserName(@Param("username") String username);

}
