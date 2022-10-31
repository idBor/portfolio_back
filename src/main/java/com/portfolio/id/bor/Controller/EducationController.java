package com.portfolio.id.bor.Controller;

import com.portfolio.id.bor.DTO.EducationDTO;
import com.portfolio.id.bor.Entity.Education;
import com.portfolio.id.bor.Security.Controller.Message;
import com.portfolio.id.bor.Service.EducationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@CrossOrigin(origins = {"https://portfolio-bor.web.app", "http://localhost:4200"})
public class EducationController {

    @Autowired
    EducationService educationService;

    @GetMapping("/list")
    public ResponseEntity<List<Education>> list(){
        List<Education> list = educationService.List();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEducation(@RequestBody EducationDTO educationDTO){
        //Condiciones acerca del campo "nombre"
        if (StringUtils.isBlank(educationDTO.getDegree()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if (educationService.existsByDegreeEdu(educationDTO.getDegree()))
            return new ResponseEntity(new Message("La educación existe"), HttpStatus.BAD_REQUEST);

        Education education = new Education(educationDTO.getDegree(), educationDTO.getDateEnd(), educationDTO.getDescription());
        educationService.save(education);
        return new ResponseEntity(new Message("Educación agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") Long id, @RequestBody EducationDTO educationDTO){
        //Condición de existencia - Id
        if (!educationService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        //Condición de existencia repetida - Degree
        if (educationService.existsByDegreeEdu(educationDTO.getDegree()) && educationService.getByDegree(educationDTO.getDegree()).get().getId() !=id)
            return new ResponseEntity(new Message("Educación ya existente"), HttpStatus.BAD_REQUEST);
        //Condición de campo requerido - Degree
        if (StringUtils.isBlank(educationDTO.getDegree()))
            return new ResponseEntity(new Message("El Título es requerido"), HttpStatus.BAD_REQUEST);

        Education education = educationService.getOne(id).get();

        education.setDegree(educationDTO.getDegree());
        education.setDateEnd(educationDTO.getDateEnd());
        education.setDescription(educationDTO.getDescription());

        educationService.save(education);
        return new ResponseEntity<>(new Message("Educación actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        //Condición de existencia - Id
        if (!educationService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);

        educationService.delete(id);

        return new ResponseEntity<>(new Message("Educación eliminada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") Long id){
        if(!educationService.existsById(id)) {
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        }
        Education education = educationService.getOne(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }
}
