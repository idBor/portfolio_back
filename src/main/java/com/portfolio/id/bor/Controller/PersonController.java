package com.portfolio.id.bor.Controller;

import com.portfolio.id.bor.DTO.PersonDTO;
import com.portfolio.id.bor.Entity.Person;
import com.portfolio.id.bor.Security.Controller.Message;
import com.portfolio.id.bor.Service.PersonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
@CrossOrigin(origins = {"", "http://localhost:4200"})
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/list")
    public ResponseEntity<List<Person>> list(){
        List<Person> list = personService.List();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonDTO personDTO){
        //Condiciones acerca del campo "nombre"
        if (StringUtils.isBlank(personDTO.getName()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if (personService.existsByName(personDTO.getName()))
            return new ResponseEntity(new Message("La persona existe"), HttpStatus.BAD_REQUEST);

        Person pers = new Person(personDTO.getName(), personDTO.getSurname(), personDTO.getAge(), personDTO.getTittle(), personDTO.getImg(), personDTO.getDescription());
        personService.save(pers);
        return new ResponseEntity(new Message("Persona agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") Long id, @RequestBody PersonDTO personDTO){
        //Condición de existencia - Id
        if (!personService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.NOT_FOUND);
        //Condición de existencia repetida - Nombre
        if (personService.existsByName(personDTO.getName()) && personService.getByName(personDTO.getName()).get().getId() !=id)
            return new ResponseEntity(new Message("Persona ya existente"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personDTO.getName()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personDTO.getSurname()))
            return new ResponseEntity(new Message("El Apellido es requerido"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personDTO.getTittle()))
            return new ResponseEntity(new Message("El Titulo es requerido"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personDTO.getImg()))
            return new ResponseEntity(new Message("La imagen es requerida"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(personDTO.getDescription()))
            return new ResponseEntity(new Message("La imagen es requerida"), HttpStatus.BAD_REQUEST);

        Person person = personService.getOne(id).get();
        person.setName(personDTO.getName());
        person.setSurname(personDTO.getSurname());
        person.setAge(personDTO.getAge());
        person.setTittle(personDTO.getTittle());
        person.setImg(personDTO.getImg());
        person.setDescription(personDTO.getDescription());

        personService.save(person);
        return new ResponseEntity<>(new Message("Persona actualizada"), HttpStatus.OK);
    }

    /*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        //Condición de existencia - Id
        if (!personService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);

        personService.delete(id);

        return new ResponseEntity<>(new Message("Persona eliminada"), HttpStatus.OK);
    }*/

    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") Long id){
        if(!personService.existsById(id)) {
            return new ResponseEntity(new Message("no existe"), HttpStatus.BAD_REQUEST);
        }
        Person pers = personService.getOne(id).get();
        return new ResponseEntity(pers, HttpStatus.OK);
    }
}
