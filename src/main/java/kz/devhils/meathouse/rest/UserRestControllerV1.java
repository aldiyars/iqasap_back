package kz.devhils.meathouse.rest;

import kz.devhils.meathouse.model.dtos.AdminUserDto;
import kz.devhils.meathouse.model.dtos.CreateUserDto;
import kz.devhils.meathouse.model.dtos.UserDto;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.entities.Users;
import kz.devhils.meathouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/users/")
@AllArgsConstructor
public class UserRestControllerV1 {
    private UserService userService;


    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        Users user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<AdminUserDto> getUserByIdForAdmin(@PathVariable(name = "id") Long id) {
        Users user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "user/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto user){

        Users user1 = userService.findByEmail(user.getEmail());
        if(user1 != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserProfile newProfile = new UserProfile();

        newProfile.setAddress(user.getAddress());
        newProfile.setTel((user.getTel()));
        newProfile.setImgUrl(user.getImgUrl());
        newProfile.setLat(user.getLat());
        newProfile.setLng(user.getLng());

        UserProfile userProfile = userService.registerProfile(newProfile);


        Users newUser = new Users();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setProfile(userProfile);
        Users result = userService.register(newUser);

        return new ResponseEntity(result, HttpStatus.OK);
    }

}
