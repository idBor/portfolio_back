package com.portfolio.id.bor.Controller;

import com.portfolio.id.bor.DTO.ProyectsDTO;
import com.portfolio.id.bor.Entity.Proyects;
import com.portfolio.id.bor.Security.Controller.Message;
import com.portfolio.id.bor.Service.ProyectsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyects")
@CrossOrigin(origins = {"https://portfoliobor.herokuapp.com", "http://localhost:4200"})
public class ProyectsController {

    @Autowired
    ProyectsService proyectsService;

    @GetMapping("/list")
    public ResponseEntity<List<Proyects>> list(){
        List<Proyects> list = proyectsService.List();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEducation(@RequestBody ProyectsDTO proyectsDTO){
        //Condiciones acerca del campo "nombre"
        if (StringUtils.isBlank(proyectsDTO.getName()))
            return new ResponseEntity(new Message("El nombre es requerido"), HttpStatus.BAD_REQUEST);
        if (proyectsService.existsByName(proyectsDTO.getName()))
            return new ResponseEntity(new Message("El proyecto ya existe"), HttpStatus.BAD_REQUEST);

        Proyects proyects = new Proyects(proyectsDTO.getName(), proyectsDTO.getDateEnd(), proyectsDTO.getLink(), proyectsDTO.getDescription(), proyectsDTO.getImg());
        proyectsService.save(proyects);
        return new ResponseEntity(new Message("Proyecto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") Long id, @RequestBody ProyectsDTO proyectsDTO){
        //Condición de existencia - Id
        if (!proyectsService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);
        //Condición de existencia repetida - Degree
        if (proyectsService.existsByName(proyectsDTO.getName()) && proyectsService.getByName(proyectsDTO.getName()).get().getId() !=id)
            return new ResponseEntity(new Message("Proyecto ya existente"), HttpStatus.BAD_REQUEST);
        //Condición de campo requerido - Degree
        if (StringUtils.isBlank(proyectsDTO.getName()))
            return new ResponseEntity(new Message("El Nombre es requerido"), HttpStatus.BAD_REQUEST);

        Proyects proyects = proyectsService.getOne(id).get();
        proyects.setName(proyectsDTO.getName());
        proyects.setDateEnd(proyectsDTO.getDateEnd());
        proyects.setLink(proyectsDTO.getLink());
        proyects.setDescription(proyectsDTO.getDescription());
        proyects.setImg(proyectsDTO.getImg());

        proyectsService.save(proyects);
        return new ResponseEntity<>(new Message("Proyecto actualizada"), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        //Condición de existencia - Id
        if (!proyectsService.existsById(id))
            return new ResponseEntity(new Message("El Id no existe"), HttpStatus.BAD_REQUEST);

        proyectsService.delete(id);

        return new ResponseEntity<>(new Message("Proyecto eliminado"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyects> getById(@PathVariable("id") Long id){
        if(!proyectsService.existsById(id)) {
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyects proyects = proyectsService.getOne(id).get();
        return new ResponseEntity(proyects, HttpStatus.OK);
    }
}
