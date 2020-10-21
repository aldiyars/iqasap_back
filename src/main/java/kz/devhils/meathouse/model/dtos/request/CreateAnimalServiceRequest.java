package kz.devhils.meathouse.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateAnimalServiceRequest {
    private Long animalId;
    private Long serviceId;
    private double cost;
}
