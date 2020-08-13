package kz.devhils.meathouse.controller.rest;


import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.AuthMobileDto;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.entities.Users;
import kz.devhils.meathouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/mobile")
@AllArgsConstructor
public class AuthMobilRestController {

    private final UserService userService;

    @PostMapping(value = "register")
    @ApiOperation("")
    public ResponseEntity<?> createUser(@RequestBody AuthMobileDto authMobileDto){
        if (userService.findByTel(authMobileDto.getTel()) != null){
            return new ResponseEntity<>("Мұндай телефон базада бар!", HttpStatus.BAD_REQUEST);
        }

        UserProfile userProfile = new UserProfile();
        UserProfile profile = userService.registerProfile(userProfile);
        Users users = new Users();
        users.setTel(authMobileDto.getTel());
        users.setPassword(authMobileDto.getPassword());
        users.setProfile(profile);
        Users result = userService.authMobile(users);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
