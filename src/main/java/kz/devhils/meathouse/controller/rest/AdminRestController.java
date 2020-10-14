package kz.devhils.meathouse.controller.rest;


import kz.devhils.meathouse.model.dtos.AdminUserDto;
import kz.devhils.meathouse.model.dtos.CreateUserDto;
import kz.devhils.meathouse.model.dtos.UpdateUserDto;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.mappers.UserMapper;
import kz.devhils.meathouse.repositories.PhotoRepo;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/user")
public class AdminRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PhotoRepo photoRepo;

    @GetMapping(value = "{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        AdminUserDto result = AdminUserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable (value = "id") Long id){
        User user = userService.findById(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public ResponseEntity<User> getAllUser(){
        List<User> users = userService.getAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDto user){
        User result = userService.updateUser(userMapper.toEntity(user));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto user){

        User checkUser = userService.findByTel(user.getTel());
        if(checkUser != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserProfile newProfile = new UserProfile();
        Photo photo = new Photo();
        Photo newPhoto = photoRepo.save(photo);

        newProfile.setAddress(user.getAddress());
        newProfile.setPhoto(newPhoto);
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

        return new ResponseEntity(AdminUserDto.fromUser(result), HttpStatus.OK);
    }

}
