package org.usermicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usermicroservice.entities.User;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailIgnoreCase(String emailId);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
