package kz.devhils.meathouse.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalServiceResponse {
    private Long id;
    private String animalName;
    private String serviceName;
    private Double cost;
    private Long  animalId;
    private Long serviceId;
}
