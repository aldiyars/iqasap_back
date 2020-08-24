package kz.devhils.meathouse.model.dtos;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private Long tel;
    private String password;
}
