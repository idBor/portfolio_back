package com.portfolio.id.bor.Repository;

import com.portfolio.id.bor.Entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEducationRepository extends JpaRepository<Education, Long> {

    public Optional<Education> findByDegree(String degree);

    public boolean existsByDegree(String degree);

}
