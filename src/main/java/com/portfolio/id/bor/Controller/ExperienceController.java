package com.portfolio.id.bor.Controller;

import com.portfolio.id.bor.DTO.ExperienceDTO;
import com.portfolio.id.bor.Entity.Experience;
import com.portfolio.id.bor.Security.Controller.Message;
import com.portfolio.id.bor.Service.ExperienceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
@CrossOrigin(origins = {"", "http://localhost:4200"})
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list(){
        List<Experience> list = experienceService.List();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienceDTO experienceDTO){
        //Condiciones acerca del campo "nombre"
        if (StringUtils.isBlank(experienceDTO.getName()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if (experienceService.existsByName(experienceDTO.getName()))
            return new ResponseEntity(new Message("La experiencia existe"), HttpStatus.BAD_REQUEST);

        Experience expe = new Experience(experienceDTO.getName(), experienceDTO.getDateStart(), experienceDTO.getDateEnd(), experienceDTO.getDescription());
        experienceService.save(expe);
        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") Long id, @RequestBody ExperienceDTO experienceDTO){
        //Condición de existencia - Id
        if (!experienceService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        //Condición de existencia repetida - Nombre
        if (experienceService.existsByName(experienceDTO.getName()) && experienceService.getByName(experienceDTO.getName()).get().getId() !=id)
            return new ResponseEntity(new Message("Experiencia ya existente"), HttpStatus.BAD_REQUEST);
        //Condición de campo requerido - Nombre
        if (StringUtils.isBlank(experienceDTO.getName()))
            return new ResponseEntity(new Message("El Nombre es requerido"), HttpStatus.BAD_REQUEST);

        Experience experience = experienceService.getOne(id).get();
        experience.setName(experienceDTO.getName());
        experience.setDateStart(experienceDTO.getDateStart());
        experience.setDateEnd(experienceDTO.getDateEnd());
        experience.setDescription(experienceDTO.getDescription());

        experienceService.save(experience);
        return new ResponseEntity<>(new Message("Experiencia actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        //Condición de existencia - Id
        if (!experienceService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);

        experienceService.delete(id);

        return new ResponseEntity<>(new Message("Experiencia eliminada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") Long id){
        if(!experienceService.existsById(id)) {
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        }
        Experience experience = experienceService.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }
}
