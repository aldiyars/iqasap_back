package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.request.CreateOrderRequest;
import kz.devhils.meathouse.model.dtos.response.OrderResponse;
import kz.devhils.meathouse.model.entities.Order;

import java.util.List;

public interface OrderMapper {
    Order toEntity(CreateOrderRequest createOrderRequest);

    OrderResponse toDto(Order order);

    List<OrderResponse> toDtoList(List<Order> orders);
}
