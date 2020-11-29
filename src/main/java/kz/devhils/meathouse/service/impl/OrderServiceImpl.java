package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Order;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.repositories.OrderRepo;
import kz.devhils.meathouse.repositories.StatusRepo;
import kz.devhils.meathouse.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private StatusRepo statusRepo;

    @Override
    public Order saveOrder(Order order) {
        Status status = statusRepo.findByName("pending");
        order.setStatus(status);
        Order result = orderRepo.save(order);
        return result;
    }

    @Override
    public Order findOrderById(Long id) {
        Order result = orderRepo.getOne(id);
        return result;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> result = orderRepo.findAll();
        return result;
    }

    @Override
    public Order updateOrder(Order order) {
        if(order.getId() != null && orderRepo.findById(order.getId()).orElse(null) != null){
            orderRepo.save(order);
        }
        return order;
    }

    @Override
    public Order updateOrderStatus(Long orderId, Long statusId) {
        Order order = orderRepo.getOne(orderId);
        Status status = statusRepo.getOne(statusId);
        order.setStatus(status);
        Order result = orderRepo.save(order);
        return result;
    }

    @Override
    public void deleteOrder(Order order) {
        orderRepo.delete(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public Order updateStatusById(Long id, Status status) {
        Order order = orderRepo.getOne(id);
        order.setStatus(status);
        orderRepo.save(order);
        return order;
    }

    @Override
    public List<Order> getByClientId(Long id) {
        List<Order> result = orderRepo.getByClient_Id(id);
        return result;
    }
}
