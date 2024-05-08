package com.outreach.rest.repository;

import com.outreach.rest.model.ERole;
import com.outreach.rest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
