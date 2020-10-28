package kz.devhils.meathouse.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private Long id;
    private String lastName;
    private String firstName;
    private Long tel;
    private String address;
}
