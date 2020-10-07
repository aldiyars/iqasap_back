package kz.devhils.meathouse.controller.rest;


import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.request.AnimalCreate;
import kz.devhils.meathouse.model.dtos.response.AnimalCreateResp;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.Statuses;
import kz.devhils.meathouse.model.dtos.request.AnimalProfileReq;
import kz.devhils.meathouse.model.mappers.AnimalProfileMapper;
import kz.devhils.meathouse.service.AnimalService;
import kz.devhils.meathouse.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/animals")
@AllArgsConstructor
public class AnimalRestController {

    private AnimalService animalService;
    private AnimalProfileMapper animalProfileMapper;
    private PhotoService photoService;


    public Photo uploadFile(MultipartFile file) {
        String fileName = photoService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path("downloadFile/")
                .path(fileName)
                .toUriString();

        Photo fileResourceDto = Photo.builder()
                .fileDownloadUri(fileDownloadUri)
                .fileName(fileName)
                .size(file.getSize())
                .fileType(file.getContentType())
                .build();

        Photo fileResource = photoService.save(fileResourceDto);
        return fileResource;
    }
    @GetMapping()
    @ApiOperation("Список всех Animal")
    public ResponseEntity<Animal> getAll(){
        List<Animal> animals = animalService.getAnimals();
        return new ResponseEntity(animals, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation("Создание Animal")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addAnimal(@RequestBody AnimalCreate animalCreate, @RequestBody MultipartFile file){
        Photo photo = uploadFile(file);
        Animal animal = AnimalCreate.fromDto(animalCreate);
        animal.setPhoto(photo);
        animal = animalService.saveAnimal(animal);

        AnimalCreateResp result = AnimalCreateResp.fromEntity(animal);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение Animal по ID")
    public ResponseEntity<Animal> getAnimalById(@PathVariable (name = "id") Long id){
        Animal result = animalService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("Обновить animal")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal){
        if (animal.getId() == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Animal result = animalService.updateAnimal(animal);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation("Удалить Animal")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAnimal(@RequestBody Animal animal){
        animalService.deleteAnimal(animal);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удалить Animal по ID")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAnimalById(@PathVariable Long id){
        animalService.deleteAnimalById(id);
        return new ResponseEntity<>("Deleted by ID:", HttpStatus.OK);
    }

    //Children || AnimalProfile

    @GetMapping("children")
    @ApiOperation("Получение список всех AnimalProfiles")
    public ResponseEntity<?> findAllAnimalProfile(){
        List<AnimalProfile> animalProfiles = animalService.getAllProfiles();
        return new ResponseEntity(animalProfiles, HttpStatus.OK);
    }

    @GetMapping("children/{id}")
    @ApiOperation("Получение список всех Profiles по Animal_ID")
    public ResponseEntity<AnimalProfile> getAllAnimalProfileByAnimalId(@PathVariable (name = "id") Long id){
        Animal animal = animalService.findById(id);
        if (animal == null){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        List<AnimalProfile> result = animalService.getAllByAnimalId(id);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("child/{id}")
    @ApiOperation("Получение AnimalProfile по ID")
    public ResponseEntity<AnimalProfile> getOne(@PathVariable Long id){
        AnimalProfile result = animalService.getProfileById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("child")
    @ApiOperation("Создание AnimalProfile")
    //    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addProfile(@RequestBody AnimalProfileReq animalProfile,@RequestBody MultipartFile multipartFile){
        System.out.println(animalProfileMapper.toEntity(animalProfile));
        AnimalProfile result = animalService.saveProfile(animalProfileMapper.toEntity(animalProfile));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


//    public AnimalProfile toAnimalProfile(AnimalProfileDto animalProfileDto){
//        AnimalProfile profile = new AnimalProfile();
//        Animal animal = animalService.findById(animalProfileDto.getAnimalId());
//        profile.setAnimal(animal);
//        profile.setAge(animalProfileDto.getAge());
//        profile.setColor(animalProfileDto.getColor());
//        profile.setWeight(animalProfileDto.getWeight());
//        profile.setBreed(animalProfileDto.getBreed());
//        profile.setGender(animalProfileDto.getGender());
//        profile.setCost(animalProfileDto.getCost());
//        return profile;
//    }

    @RequestMapping(value = "child", method = RequestMethod.PUT)
    @ApiOperation("Обновить AnimalProfile")
    //    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProfile(@RequestBody AnimalProfile animalProfile){
        AnimalProfile result = animalService.updateProfile(animalProfile);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("child/setstatus/{id}")
    @ApiOperation("Изменение статус AnimalProfile по ID")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Statuses statuses){
        AnimalProfile result = animalService.getProfileById(id);
        if (result.getId() == null && result == null){
            return new ResponseEntity(id, HttpStatus.NO_CONTENT);
        }

        animalService.updateStatus(result, statuses);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("child")
    @ApiOperation("Удаление AnimalProfile")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProfile(@RequestBody AnimalProfile profile){
        animalService.deleteProfile(profile);
        return new ResponseEntity<>("Deleted AnimalProfile: {}", HttpStatus.OK);
    }

    @DeleteMapping("child/{id}")
    @ApiOperation("Удаление AnimalProfile по ID")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProfileById(@PathVariable Long id){
        animalService.deleteByIdProfile(id);
        return new ResponseEntity<>("Deleted AnimalProfile by ID: {}", HttpStatus.OK);
    }


}
