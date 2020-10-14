package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.dtos.request.CreateAnimalService;
import kz.devhils.meathouse.model.entities.AnimalService;
import kz.devhils.meathouse.model.mappers.AnimalServiceMapper;
import kz.devhils.meathouse.service.AnimalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/animalservice")
public class AnimalServiceResController {

    @Autowired
    private AnimalServiceService animalServiceService;
    @Autowired
    private AnimalServiceMapper animalServiceMapper;

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        AnimalService animalService = animalServiceService.findById(id);
        return new ResponseEntity<>(animalService, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<AnimalService> animalServices = animalServiceService.getAll();
        return new ResponseEntity<>(animalServices, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody CreateAnimalService createAnimalService){
        AnimalService result = animalServiceService.add(animalServiceMapper.toEntity(createAnimalService));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        animalServiceService.deleteById(id);
        return new ResponseEntity<>("Deleted article by ID: ", HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody AnimalService animalService){
        AnimalService result = animalServiceService.update(animalService);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
