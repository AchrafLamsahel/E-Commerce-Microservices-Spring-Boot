package org.usermicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usermicroservice.entities.Role;
import org.usermicroservice.enums.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(ERole eRole);
}
