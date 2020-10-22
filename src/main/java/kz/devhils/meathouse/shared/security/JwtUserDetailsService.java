package kz.devhils.meathouse.shared.security;

import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.shared.security.jwt.JwtUser;
import kz.devhils.meathouse.shared.security.jwt.JwtUserFactory;
import kz.devhils.meathouse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private  final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String telephone) throws UsernameNotFoundException {
        User user = userService.findByTel(telephone);

        if (user == null){
            throw  new UsernameNotFoundException("User with telephone: " + telephone + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByTelephone - user with telephone: {} successfully loaded", telephone);
        return jwtUser;
    }
}
