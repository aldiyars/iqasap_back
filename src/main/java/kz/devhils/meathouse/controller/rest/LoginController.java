package kz.devhils.meathouse.controller.rest;

import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.AuthenticationRequestDto;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.shared.security.jwt.JwtTokenProvider;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;


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
}
