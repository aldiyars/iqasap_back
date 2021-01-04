package kz.devhils.meathouse.controller.rest;


import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.AuthMobileDto;
import kz.devhils.meathouse.model.dtos.AuthenticationRequestDto;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.mappers.UserMapper;
import kz.devhils.meathouse.service.UserService;
import kz.devhils.meathouse.shared.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://iqasap.taq.kz")
@RequestMapping(value = "/api/v1/auth/")
public class AuthRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("login")
    @ApiOperation("Вход в систему по номеру телефона")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String tel = requestDto.getTel().toString();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tel, requestDto.getPassword()));
            User user = userService.findByTel(tel);

            if (user == null){
                throw new UsernameNotFoundException("User with tel: " + tel + " not found");
            }

            String token = jwtTokenProvider.createToken(tel, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("tel", tel);
            response.put("token", token);
            response.put("id", user.getId());

            return ResponseEntity.ok(response);

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid phone or password");
        }
    }

    @PostMapping(value = "register")
    @ApiOperation("Регистрация по номеру телефона")
    public ResponseEntity<?> createUser(@RequestBody AuthMobileDto authMobileDto){
        if (userService.findByTel(authMobileDto.getTel().toString()) != null){
            return new ResponseEntity<>("Мұндай телефон базада бар!", HttpStatus.BAD_REQUEST);
        }

        UserProfile userProfile = new UserProfile();
        UserProfile profile = userService.registerProfile(userProfile);
        User user = new User();
        user.setTel(authMobileDto.getTel());
        user.setPassword(authMobileDto.getPassword());
        user.setUserProfile(profile);
        User result = userService.authMobile(user);
        return new ResponseEntity<>(userMapper.toDto(result), HttpStatus.CREATED);
    }
}
