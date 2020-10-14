package kz.devhils.meathouse.controller.rest;


import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.request.AnimalCreate;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.model.dtos.request.AnimalProfileReq;
import kz.devhils.meathouse.model.mappers.AnimalProfileMapper;
import kz.devhils.meathouse.service.AnimalService;
import kz.devhils.meathouse.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/animals")
public class AnimalRestController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalProfileMapper animalProfileMapper;
    @Autowired
    private PhotoService photoService;


    public Photo uploadFile(MultipartFile file) {
        String fileName = photoService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/photo/")
                .path("downloadFile/")
                .path(fileName)
                .toUriString();

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/photo/")
                .path("get/")
                .path(fileName)
                .toUriString();

        Photo fileResourceDto = Photo.builder()
                .fileDownloadUri(fileDownloadUri)
                .fileUrl(fileUrl)
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

    @PostMapping(consumes = {"multipart/form-data"})
    @ApiOperation("Создание Animal")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addAnimal(@ModelAttribute AnimalCreate animalCreate, @RequestPart MultipartFile file){
        Photo photo = uploadFile(file);
        Animal animal = AnimalCreate.fromDto(animalCreate);
        animal.setPhoto(photo);
        animal = animalService.saveAnimal(animal);

        return new ResponseEntity<>(animal, HttpStatus.CREATED);
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
    public ResponseEntity<?> addProfile(@ModelAttribute AnimalProfileReq animalProfileReq,
                                        @RequestPart MultipartFile multipartFile){
        Photo photos = uploadFile(multipartFile);

        AnimalProfile animalProfile = animalProfileMapper.toEntity(animalProfileReq);
        animalProfile.setPhotos(Collections.singletonList(photos));

        AnimalProfile result = animalService.saveProfile(animalProfile);
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
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status){
        AnimalProfile result = animalService.getProfileById(id);
        if (result.getId() == null && result == null){
            return new ResponseEntity(id, HttpStatus.NO_CONTENT);
        }

        animalService.updateStatus(result, status);
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
