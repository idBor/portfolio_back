package com.portfolio.id.bor.Service;

import com.portfolio.id.bor.Entity.Proyects;
import com.portfolio.id.bor.Repository.IProyectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectsService {

    @Autowired
    IProyectsRepository iProyectsRepository;

    public List<Proyects> List(){
        return iProyectsRepository.findAll();
    }

    public Optional<Proyects> getOne(Long id){
        return iProyectsRepository.findById(id);
    }

    public Optional<Proyects> getByName(String name){
        return iProyectsRepository.findByName(name);
    }

    public void save(Proyects proyects){
        iProyectsRepository.save(proyects);
    }

    public void delete(Long id){
        iProyectsRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return iProyectsRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return iProyectsRepository.existsByName(name);
    }
}
