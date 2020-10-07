package kz.devhils.meathouse.model.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kz.devhils.meathouse.model.entities.Roles;
import kz.devhils.meathouse.model.entities.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long tel;
    private List<Roles> roles;
    private UserProfile profile;
}
