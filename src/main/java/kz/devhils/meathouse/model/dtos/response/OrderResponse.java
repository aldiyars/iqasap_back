package kz.devhils.meathouse.model.dtos.response;

import kz.devhils.meathouse.model.entities.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private ClientResponse client;
    private String paymentTypeName;
    private String statusName;
    private List<OrderAnimalResponse> orderAnimals;
    private Double total;
    private String createdAt;
}
