package kz.devhils.meathouse.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthMobileDto {

    private Long tel;
    private String password;
}
