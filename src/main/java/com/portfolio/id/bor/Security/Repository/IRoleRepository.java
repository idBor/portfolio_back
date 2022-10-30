package com.portfolio.id.bor.Security.Repository;

import com.portfolio.id.bor.Security.Entity.Role;
import com.portfolio.id.bor.Security.Enum.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    // Role 'Optional', to find by name
    Optional<Role> findByRoleName(RoleName roleName);
}
