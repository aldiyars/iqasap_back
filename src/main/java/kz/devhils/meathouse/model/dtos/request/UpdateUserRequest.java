package kz.devhils.meathouse.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private Long id;
    private String firstName;
    private String email;
    private String lastName;
    private String address;
    private Long tel;
}
