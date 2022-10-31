package com.portfolio.id.bor.Repository;

import com.portfolio.id.bor.Entity.Proyects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProyectsRepository extends JpaRepository<Proyects, Long> {
    Optional<Proyects> findByName(String name);

    boolean existsByName(String name);
}
