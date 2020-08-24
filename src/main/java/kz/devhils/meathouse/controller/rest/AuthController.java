package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.dtos.AuthenticationRequestDto;
import kz.devhils.meathouse.model.entities.Users;
import kz.devhils.meathouse.shared.security.jwt.JwtTokenProvider;
import kz.devhils.meathouse.service.UserService;
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
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String tel = requestDto.getTel().toString();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(tel, requestDto.getPassword()));
            Users user = userService.findByEmail(tel);

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
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
