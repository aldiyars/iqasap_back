package kz.devhils.meathouse.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAnimalResponse {
    private Long id;
    private AnimalProfileResponse animalProfile;
    private List<AnimalServiceResponse> animalServices;
}
