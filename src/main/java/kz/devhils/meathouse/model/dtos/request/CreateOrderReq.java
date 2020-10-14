package kz.devhils.meathouse.model.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateOrderReq {

    private Long clientId;
    private int isPayed;
    private Long paymentTypeId;
    private List<CreateAnimalOrder> createAnimalOrders;
}
