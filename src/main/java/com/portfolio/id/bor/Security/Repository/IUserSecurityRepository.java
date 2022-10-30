package com.portfolio.id.bor.Security.Repository;

import com.portfolio.id.bor.Security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSecurityRepository extends JpaRepository<User, Long> {
    //User 'Optional', to find by name
    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);

}
