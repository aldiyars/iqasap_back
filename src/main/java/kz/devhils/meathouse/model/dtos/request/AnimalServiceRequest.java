package kz.devhils.meathouse.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalServiceRequest {
    private Long id;
    private Long animalId;
    private Long serviceId;
    private double cost;
}
