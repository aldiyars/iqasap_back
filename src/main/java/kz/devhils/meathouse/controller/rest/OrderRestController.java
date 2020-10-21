package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.dtos.request.CreateOrderReq;
import kz.devhils.meathouse.model.entities.Order;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.model.mappers.OrderMapper;
import kz.devhils.meathouse.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @GetMapping()
    public ResponseEntity<?> getAllOrder(){
        List<Order> result = orderService.getAllOrders();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id){
        Order result = orderService.findOrderById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addOrder(@RequestBody CreateOrderReq order){
        Order result = orderService.saveOrder(orderMapper.toEntity(order));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateOrder(@RequestBody Order order){
        Order result = orderService.updateOrder(order);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status){
        Order result = orderService.updateStatusById(id, status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteOrder(@RequestBody Order order){
        orderService.deleteOrder(order);
        return new ResponseEntity<>("Deleted Order",HttpStatus.OK);
    }

    @DeleteMapping("id")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Deleted Order By ID: {}", HttpStatus.OK);
    }
}