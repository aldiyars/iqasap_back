package kz.devhils.meathouse.rest;

import kz.devhils.meathouse.dto.AdminUserDto;
import kz.devhils.meathouse.dto.CreateUserDto;
import kz.devhils.meathouse.model.UserProfile;
import kz.devhils.meathouse.model.Users;
import kz.devhils.meathouse.repositories.UserRepository;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1/main/")
public class MainRestController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public MainRestController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        Users user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto user){

        UserProfile newProfile = new UserProfile();
        newProfile.setAddress(user.getAddress());
        newProfile.setTel((user.getTel()));
        newProfile.setImgUrl(user.getImgUrl());
        newProfile.setLat(user.getLat());
        newProfile.setLng(user.getLng());

        UserProfile userProfile = userService.registerProfile(newProfile);

        Date currentDate = Calendar.getInstance().getTime();

        Users newUser = new Users();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setProfile(userProfile);
//        newUser.setCreatedAt(currentDate);

        System.out.println(currentDate);

        Users result = userService.register(newUser);

        return new ResponseEntity(result, HttpStatus.OK);
    }


}
