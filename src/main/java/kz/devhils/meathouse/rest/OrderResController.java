package kz.devhils.meathouse.rest;

import kz.devhils.meathouse.model.entities.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/")
public class OrderResController {



    @GetMapping(value = "")
    public ResponseEntity<Order> getAllOrder(){


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
