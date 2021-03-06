package kz.devhils.meathouse.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.devhils.meathouse.model.entities.Role;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;
import lombok.Data;

import java.util.List;



@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long tel;
    private String email;
    private List<Role> roles;
    private UserProfile userProfile;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRoles(roles);
        user.setUserProfile(userProfile);
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setTel(user.getTel());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setUserProfile(user.getUserProfile());
        adminUserDto.setRoles(user.getRoles());
        return adminUserDto;
    }
}
