package com.portfolio.id.bor.Controller;

import com.portfolio.id.bor.DTO.SkillsDTO;
import com.portfolio.id.bor.Entity.Skills;
import com.portfolio.id.bor.Security.Controller.Message;
import com.portfolio.id.bor.Service.SkillsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = {"https://portfolio-idbor.web.app", "http://localhost:4200"})
public class SkillsController {

    @Autowired
    SkillsService skillsService;

    @GetMapping("/list")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = skillsService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SkillsDTO skillsDTO){
        //Condiciones acerca del campo "nombre"
        if (StringUtils.isBlank(skillsDTO.getName()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if (skillsService.existsByName(skillsDTO.getName()))
            return new ResponseEntity(new Message("Skill no existe"), HttpStatus.BAD_REQUEST);

        Skills skills = new Skills(skillsDTO.getName(), skillsDTO.getPercentage());
        skillsService.save(skills);
        return new ResponseEntity(new Message("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") Long id, @RequestBody SkillsDTO skillsDTO){
        //Condición de existencia - Id
        if (!skillsService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        //Condición de existencia repetida - Nombre
        if (skillsService.existsByName(skillsDTO.getName()) && skillsService.getByName(skillsDTO.getName())
                .get().getId() !=id)
            return new ResponseEntity(new Message("Skill ya existente"), HttpStatus.BAD_REQUEST);
        //Condición de campo requerido - Nombre
        if (StringUtils.isBlank(skillsDTO.getName()))
            return new ResponseEntity(new Message("El Nombre es requerido"), HttpStatus.BAD_REQUEST);

        Skills skills = skillsService.getOne(id).get();
        skills.setName(skillsDTO.getName());
        skills.setPercentage(skillsDTO.getPercentage());

        skillsService.save(skills);
        return new ResponseEntity<>(new Message("Skill actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        //Condición de existencia - Id
        if (!skillsService.existsById(id))
            return new ResponseEntity(new Message("Skill no existente"), HttpStatus.BAD_REQUEST);

        skillsService.delete(id);

        return new ResponseEntity<>(new Message("Skill eliminada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") Long id){
        if(!skillsService.existsById(id)) {
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        }
        Skills skills = skillsService.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
}
