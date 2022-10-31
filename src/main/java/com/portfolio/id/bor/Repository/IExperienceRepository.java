package com.portfolio.id.bor.Repository;

import com.portfolio.id.bor.Entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IExperienceRepository extends JpaRepository<Experience, Long> {

    Optional<Experience> findByName(String name);

    boolean existsByName(String name);
}
