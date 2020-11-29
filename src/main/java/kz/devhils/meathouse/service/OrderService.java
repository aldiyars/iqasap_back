package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Order;
import kz.devhils.meathouse.model.entities.Status;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);
    Order findOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrder(Order order);
    Order updateOrderStatus (Long orderId, Long statusId);
    void deleteOrder(Order order);
    void deleteOrderById(Long id);
    Order updateStatusById(Long id, Status status);
    List<Order> getByClientId(Long id);
}
