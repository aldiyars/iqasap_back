package kz.devhils.meathouse.shared.security.jwt;

import kz.devhils.meathouse.model.entities.Role;
import kz.devhils.meathouse.model.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static  JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserProfile(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getCreatedAt()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles){
        return userRoles.stream().map(roles -> new SimpleGrantedAuthority(roles.getName())
        ).collect(Collectors.toList());
    }
}
