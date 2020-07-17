package kz.devhils.meathouse.rest;

import kz.devhils.meathouse.dto.AuthenticationRequestDto;
import kz.devhils.meathouse.model.Users;
import kz.devhils.meathouse.security.jwt.JwtTokenProvider;
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
public class AuthControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            Users user = userService.findByEmail(email);

            if (user == null){
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }


            String token = jwtTokenProvider.createToken(email, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);

        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
