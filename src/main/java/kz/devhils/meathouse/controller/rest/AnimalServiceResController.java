package kz.devhils.meathouse.controller.rest;

import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.request.AnimalServiceRequest;
import kz.devhils.meathouse.model.dtos.request.CreateAnimalServiceRequest;
import kz.devhils.meathouse.model.dtos.response.AnimalServiceResponse;
import kz.devhils.meathouse.model.entities.AnimalService;
import kz.devhils.meathouse.model.mappers.AnimalServiceMapper;
import kz.devhils.meathouse.service.AnimalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping(value = "/api/v1/animalservice")
public class AnimalServiceResController {

    @Autowired
    private AnimalServiceService animalServiceService;
    @Autowired
    private AnimalServiceMapper animalServiceMapper;

    @GetMapping("{id}")
    @ApiOperation("Получение AnimalService по ID")
    public ResponseEntity<?> getById(@PathVariable Long id){
        AnimalService animalService = animalServiceService.findById(id);
        return new ResponseEntity<>(animalServiceMapper.toDto(animalService), HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation("Получение вес список AnimalServices")
    public ResponseEntity<?> getAll(){
        List<AnimalService> animalServices = animalServiceService.getAll();

        List<AnimalServiceResponse> animalServiceResponses =
                animalServices.stream().map(e -> animalServiceMapper.toDto(e)).collect(Collectors.toList());
        return new ResponseEntity<>(animalServiceResponses, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation("Создание AnimalService")
    public ResponseEntity<?> save(@RequestBody CreateAnimalServiceRequest createAnimalServiceRequest){
        AnimalService result = animalServiceService.add(animalServiceMapper.inCreateEntityToEntity(createAnimalServiceRequest));
        return new ResponseEntity<>(animalServiceMapper.toDto(result), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление AnimalService по ID")
    public ResponseEntity<?> delete(@PathVariable Long id){
        animalServiceService.deleteById(id);
        return new ResponseEntity<>("Deleted article by ID: ", HttpStatus.OK);
    }

    @PutMapping()
    @ApiOperation("Обнавление AnimalService")
    public ResponseEntity<?> update(@RequestBody AnimalServiceRequest animalServiceRequest){
        AnimalService result = animalServiceService.update(animalServiceMapper.toEntity(animalServiceRequest));
        return new ResponseEntity<>(animalServiceMapper.toDto(result), HttpStatus.OK);
    }

}
