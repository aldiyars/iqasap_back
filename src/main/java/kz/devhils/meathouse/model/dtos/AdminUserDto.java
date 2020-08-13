package kz.devhils.meathouse.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.devhils.meathouse.model.entities.Roles;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.entities.Users;
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
    private List<Roles> roles;
    private UserProfile userProfile;

    public Users toUser() {
        Users user = new Users();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRoles(roles);
        user.setProfile(userProfile);
        return user;
    }

    public static AdminUserDto fromUser(Users user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setTel(user.getTel());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setUserProfile(user.getProfile());
        adminUserDto.setRoles(user.getRoles());
        return adminUserDto;
    }
}
