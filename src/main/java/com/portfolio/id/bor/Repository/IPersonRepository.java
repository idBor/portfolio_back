package com.portfolio.id.bor.Repository;

import com.portfolio.id.bor.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    public Optional<Person> findByName(String name);
    public boolean existsByName(String name);
}
