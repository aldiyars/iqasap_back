package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.request.CreateOrderReq;
import kz.devhils.meathouse.model.entities.Order;

public interface OrderMapper {
    Order toEntity(CreateOrderReq createOrderReq);
}
