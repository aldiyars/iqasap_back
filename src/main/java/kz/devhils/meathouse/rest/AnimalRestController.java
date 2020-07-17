package kz.devhils.meathouse.rest;


import kz.devhils.meathouse.model.Animal;
import kz.devhils.meathouse.repositories.AnimalProfileRepo;
import kz.devhils.meathouse.repositories.AnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/main/")
public class AnimalRestController {

    @Autowired
    private AnimalRepo animalRepo;
    @Autowired
    private AnimalProfileRepo animalProfileRepo;

//    @GetMapping(value = "animals/{id}")
//    public ResponseEntity<List<AnimalProfile>> getAnimalById(@PathVariable(name = "id") Long id){
//
//        Animal animal = animalRepo.getOne(id);
//        Long animalId = animal.getId();
//
//
//        if (animalId == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        List<AnimalProfile> result = new ArrayList();
//
//        List<AnimalProfile> profileList =  animalProfileRepo.findAll();
//        for (int i = 0; i < profileList.size(); i++){
//            if (animal.getId() == (profileList.get(i).getAnimal().getId())){
//                result.add(profileList.get(i));
//            }
//        }
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

    @GetMapping("animals")
    public ResponseEntity<Animal> getAllAnimal(){
        List<Animal> animals = animalRepo.findAll();
        return new ResponseEntity(animals, HttpStatus.OK);
    }
}
