package com.portfolio.id.bor.Service;

import com.portfolio.id.bor.Entity.Skills;
import com.portfolio.id.bor.Repository.ISkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SkillsService {
    @Autowired
    ISkillsRepository iSkillsRepository;

    public List<Skills> list(){
        return iSkillsRepository.findAll();
    }

    public Optional<Skills> getOne(Long id){
        return iSkillsRepository.findById(id);
    }

    public Optional<Skills> getByName(String name){
        return iSkillsRepository.findByName(name);
    }

    public void save(Skills skills){
        iSkillsRepository.save(skills);
    }

    public void delete(Long id){
        iSkillsRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return iSkillsRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return iSkillsRepository.existsByName(name);
    }
}
