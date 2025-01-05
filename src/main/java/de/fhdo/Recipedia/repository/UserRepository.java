package de.fhdo.Recipedia.repository;

import de.fhdo.Recipedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}