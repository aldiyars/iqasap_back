package kz.devhils.meathouse.controller.rest;


import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.request.AnimalCreate;
import kz.devhils.meathouse.model.dtos.response.AnimalProfileResponse;
import kz.devhils.meathouse.model.dtos.response.AnimalResponse;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.model.dtos.request.AnimalProfileReq;
import kz.devhils.meathouse.model.mappers.AnimalMapper;
import kz.devhils.meathouse.model.mappers.AnimalProfileMapper;
import kz.devhils.meathouse.service.AnimalService;
import kz.devhils.meathouse.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://iqasap.taq.kz")
@RequestMapping(value = "/api/v1/animals")
public class AnimalRestController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalProfileMapper animalProfileMapper;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private AnimalMapper animalMapper;

    public Photo uploadFile(MultipartFile file) {
        String fileName = photoService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/photo/")
                .path("downloadFile/")
                .path(fileName)
                .toUriString();

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/photo/")
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

    public List<Photo> uploadMultipleFiles(MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping()
    @ApiOperation("Список всех Animal")
    public ResponseEntity<?> getAll() {
        List<Animal> animals = animalService.getAnimals();

        List<AnimalResponse> animalResponses = animals.stream().map(e -> animalMapper.toDto(e)).collect(Collectors.toList());

        return new ResponseEntity(animalResponses, HttpStatus.OK);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ApiOperation("Создание Animal")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addAnimal(@ModelAttribute AnimalCreate animalCreate, @RequestPart MultipartFile file) {
        Photo photo = uploadFile(file);
        Animal animal = AnimalCreate.fromDto(animalCreate);
        animal.setPhoto(photo);
        animal = animalService.saveAnimal(animal);

        return new ResponseEntity<>(animalMapper.toDto(animal), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ApiOperation("Получение Animal по ID")
    public ResponseEntity<Animal> getAnimalById(@PathVariable(name = "id") Long id) {
        Animal animal = animalService.findById(id);
        return new ResponseEntity(animalMapper.toDto(animal), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation("Обновить animal")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<Animal> updateAnimal(@RequestBody Animal animal) {
        if (animal.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Animal result = animalService.updateAnimal(animal);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удалить Animal по ID")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteAnimalById(@PathVariable Long id) {
        animalService.deleteAnimalById(id);
        return new ResponseEntity<>("Deleted by ID:", HttpStatus.OK);
    }

    //Children || AnimalProfile

    @GetMapping("children")
    @ApiOperation("Получение список всех AnimalProfiles")
    public ResponseEntity<?> findAllAnimalProfile() {
        List<AnimalProfile> animalProfiles = animalService.getAllProfiles();

        List<AnimalProfileResponse> animalProfileResponses =
                animalProfiles.stream().map(e -> animalProfileMapper.toDto(e)).collect(Collectors.toList());

        return new ResponseEntity(animalProfileResponses, HttpStatus.OK);
    }

    @GetMapping("children/{id}")
    @ApiOperation("Получение список всех AnimalProfiles по Animal_ID")
    public ResponseEntity<AnimalProfile> getAllAnimalProfileByAnimalId(@PathVariable(name = "id") Long id) {
        Animal animal = animalService.findById(id);
        if (animal == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        List<AnimalProfile> result = animalService.getAllByAnimalId(id);

        List<AnimalProfileResponse> animalProfileResponses =
                result.stream().map(e -> animalProfileMapper.toDto(e)).collect(Collectors.toList());

        return new ResponseEntity(animalProfileResponses, HttpStatus.OK);
    }

    @GetMapping("child/{id}")
    @ApiOperation("Получение AnimalProfile по ID")
    public ResponseEntity<AnimalProfile> getOne(@PathVariable Long id) {
        AnimalProfile result = animalService.getProfileById(id);
        return new ResponseEntity(animalProfileMapper.toDto(result), HttpStatus.OK);
    }

    @PostMapping(value = "child")
    @ApiOperation("Создание AnimalProfile")
    //    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addProfile(@ModelAttribute AnimalProfileReq animalProfileReq,
                                        @RequestPart MultipartFile files) {
//        List<Photo> photos = uploadMultipleFiles(files);
        Photo photo = uploadFile(files);

        AnimalProfile animalProfile = animalProfileMapper.toEntity(animalProfileReq);
        animalProfile.setPhotos((Collections.singletonList(photo)));

        AnimalProfile result = animalService.saveProfile(animalProfile);
        return new ResponseEntity<>(animalProfileMapper.toDto(result), HttpStatus.CREATED);
    }


    @RequestMapping(value = "child", method = RequestMethod.PUT)
    @ApiOperation("Обновить AnimalProfile")
    //    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateProfile(@RequestBody AnimalProfile animalProfile) {
        AnimalProfile result = animalService.updateProfile(animalProfile);
        return new ResponseEntity<>(animalProfileMapper.toDto(result), HttpStatus.OK);
    }

    @PostMapping("child/setstatus/{id}")
    @ApiOperation("Изменение статус AnimalProfile по ID")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status) {
        AnimalProfile result = animalService.getProfileById(id);
        if (result.getId() == null && result == null) {
            return new ResponseEntity(id, HttpStatus.NO_CONTENT);
        }

        animalService.updateStatus(result, status);
        return new ResponseEntity<>(animalProfileMapper.toDto(result), HttpStatus.OK);
    }

    @DeleteMapping("child")
    @ApiOperation("Удаление AnimalProfile")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProfile(@RequestBody AnimalProfile profile) {
        animalService.deleteProfile(profile);
        return new ResponseEntity<>("Deleted AnimalProfile: {}", HttpStatus.OK);
    }

    @DeleteMapping("child/{id}")
    @ApiOperation("Удаление AnimalProfile по ID")
//    @PreAuthorize("HasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteProfileById(@PathVariable Long id) {
        animalService.deleteByIdProfile(id);
        return new ResponseEntity<>("Deleted AnimalProfile by ID: {}", HttpStatus.OK);
    }

}
