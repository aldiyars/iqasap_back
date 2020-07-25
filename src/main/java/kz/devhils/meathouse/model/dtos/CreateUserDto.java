package kz.devhils.meathouse.model.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int tel;
    private String address;
    private String imgUrl;
    private String lat;
    private String lng;
}
