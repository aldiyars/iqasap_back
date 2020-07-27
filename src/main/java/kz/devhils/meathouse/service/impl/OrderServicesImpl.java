package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Order;
import kz.devhils.meathouse.model.entities.Statuses;
import kz.devhils.meathouse.repositories.OrderRepo;
import kz.devhils.meathouse.service.OrderServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServicesImpl implements OrderServices {

    private OrderRepo orderRepo;


    @Override
    public Order saveOrder(Order order) {
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
    public void deleteOrder(Order order) {
        orderRepo.delete(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public Order updateStatusById(Long id, Statuses statuses) {
        Order order = orderRepo.getOne(id);


        return null;
    }
}
