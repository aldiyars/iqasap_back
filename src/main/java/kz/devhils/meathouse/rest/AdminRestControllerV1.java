package kz.devhils.meathouse.rest;


import kz.devhils.meathouse.dto.AdminUserDto;
import kz.devhils.meathouse.model.Users;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {
    @Autowired
    private UserService userService;

    @GetMapping(value = "user/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id){
        Users user = userService.findById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "user/delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable (value = "id") Long id){
        Users user = userService.findById(id);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "users")
    public ResponseEntity<Users> getAllUser(){
        List<Users> users = userService.getAll();

        return new ResponseEntity(users, HttpStatus.OK);
    }

}
