package com.portfolio.id.bor.Service;

import com.portfolio.id.bor.Entity.Experience;
import com.portfolio.id.bor.Repository.IExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceService {

    @Autowired
    IExperienceRepository iExperienceRepository;

    public List<Experience> List(){
        return iExperienceRepository.findAll();
    }

    public Optional<Experience> getOne(Long id){
        return iExperienceRepository.findById(id);
    }

    public Optional<Experience> getByName(String name){
        return iExperienceRepository.findByName(name);
    }

    public void save(Experience expe) {
        iExperienceRepository.save(expe);
    }

    public void delete(Long id){
        iExperienceRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return iExperienceRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return iExperienceRepository.existsByName(name);
    }
}
