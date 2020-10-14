package kz.devhils.meathouse.controller.rest;


import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.AuthMobileDto;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/mobile")
public class AuthMobilRestController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "register")
    @ApiOperation("")
    public ResponseEntity<?> createUser(@RequestBody AuthMobileDto authMobileDto){
        if (userService.findByTel(authMobileDto.getTel()) != null){
            return new ResponseEntity<>("Мұндай телефон базада бар!", HttpStatus.BAD_REQUEST);
        }

        UserProfile userProfile = new UserProfile();
        UserProfile profile = userService.registerProfile(userProfile);
        User user = new User();
        user.setTel(authMobileDto.getTel());
        user.setPassword(authMobileDto.getPassword());
        user.setUserProfile(profile);
        User result = userService.authMobile(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
