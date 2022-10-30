package com.portfolio.id.bor.Service;

import com.portfolio.id.bor.Entity.Person;
import com.portfolio.id.bor.Repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService{

    @Autowired
    IPersonRepository iPersonRepository;

    public List<Person> List(){
        return iPersonRepository.findAll();
    }

    public Optional<Person> getOne(Long id){
        return iPersonRepository.findById(id);
    }

    public Optional<Person> getByName(String name){
        return iPersonRepository.findByName(name);
    }

    public void save(Person pers) {
        iPersonRepository.save(pers);
    }

    public void delete(Long id){
        iPersonRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return iPersonRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return iPersonRepository.existsByName(name);
    }

}
