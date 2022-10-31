package com.portfolio.id.bor.Repository;

import com.portfolio.id.bor.Entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISkillsRepository extends JpaRepository<Skills, Long> {
    Optional<Skills> findByName(String name);

    boolean existsByName(String name);
}
