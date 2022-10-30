package com.portfolio.id.bor.Service;

import com.portfolio.id.bor.Entity.Education;
import com.portfolio.id.bor.Repository.IEducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EducationService {

    @Autowired
    IEducationRepository iEducationRepository;

    public List<Education> List(){
        return iEducationRepository.findAll();
    }

    public Optional<Education> getOne(Long id){
        return iEducationRepository.findById(id);
    }

    public Optional<Education> getByDegree(String degree){
        return iEducationRepository.findByDegree(degree);
    }

    public void save(Education education){
        iEducationRepository.save(education);
    }

    public void delete(Long id){
        iEducationRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return iEducationRepository.existsById(id);
    }

    public boolean existsByDegreeEdu(String degree){
        return iEducationRepository.existsByDegree(degree);
    }
}
