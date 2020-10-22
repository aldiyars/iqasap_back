package kz.devhils.meathouse.controller.rest;


import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.CreateUserDto;
import kz.devhils.meathouse.model.dtos.request.UpdateUserRequest;
import kz.devhils.meathouse.model.dtos.response.UserResponse;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.mappers.UpdateUserMapper;
import kz.devhils.meathouse.model.mappers.UserMapper;
import kz.devhils.meathouse.repositories.UserProfileRepo;
import kz.devhils.meathouse.service.PhotoService;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/admin/user")
public class AdminRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UpdateUserMapper updateUserMapper;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private UserProfileRepo userProfileRepo;

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

    @GetMapping(value = "{id}")
    @ApiOperation("Получение User по ID")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation("Удаление User по ID")
    public ResponseEntity<?> deleteUserById(@PathVariable (value = "id") Long id){
        User user = userService.findById(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation("Получение список всех User")
    public ResponseEntity<?> getAllUser(){
        List<User> users = userService.getAll();

        List<UserResponse> userResponses =
                users.stream().map(e -> userMapper.toDto(e)).collect(Collectors.toList());

        return new ResponseEntity(userResponses, HttpStatus.OK);
    }

    @PutMapping()
    @ApiOperation("Обнавление данный User")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest user){
        User result = userService.updateUser(updateUserMapper.toEntity(user));
        return new ResponseEntity(userMapper.toDto(result), HttpStatus.OK);
    }

    @PutMapping(value = "{userId}")
    @ApiOperation("Обнавление Аватар User по ID")
    public ResponseEntity<?> updatePhoto (@PathVariable Long userId, @RequestPart MultipartFile file){
        User user = userService.findById(userId);
        UserProfile userProfile = userProfileRepo.getOne(user.getUserProfile().getId());
        Photo photo = uploadFile(file);
        userProfile.setPhoto(photo);
        userProfileRepo.save(userProfile);

        return new ResponseEntity(userMapper.toDto(user), HttpStatus.OK);

    }

    @PostMapping()
    @ApiOperation("Создание User")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto user){

        User checkUser = userService.findByTel(user.getTel().toString());
        if(checkUser != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserProfile newProfile = new UserProfile();

        newProfile.setAddress(user.getAddress());
        newProfile.setPhoto(null);
        newProfile.setLat(user.getLat());
        newProfile.setLng(user.getLng());

        UserProfile userProfile = userService.registerProfile(newProfile);

        User newUser = new User();
        newUser.setTel(user.getTel());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setUserProfile(userProfile);
        User result = userService.register(newUser);

        return new ResponseEntity(userMapper.toDto(result), HttpStatus.OK);
    }

    @GetMapping("tel/{tel}")
    @ApiOperation("Получение User по номеру телефона")
    public ResponseEntity<?> getByTel (@PathVariable(name = "tel") Long tel){
        User user = userService.findByTel(tel.toString());
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }

}
