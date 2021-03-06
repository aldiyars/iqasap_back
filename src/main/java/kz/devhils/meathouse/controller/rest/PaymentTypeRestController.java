package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.entities.PaymentType;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/payment")
@CrossOrigin(origins = "http://iqasap.taq.kz")
public class PaymentTypeRestController {

    @Autowired
    private PaymentTypeService paymentTypeService;

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        PaymentType result = paymentTypeService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<PaymentType> result = paymentTypeService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addPaymentType(@RequestBody PaymentType paymentType){
        PaymentType result = paymentTypeService.save(paymentType);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updatePaymentType(@RequestBody PaymentType paymentType){
        PaymentType result = paymentTypeService.update(paymentType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Status status){
        PaymentType result = paymentTypeService.updateStatus(id, status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        paymentTypeService.deleteById(id);
        return new ResponseEntity<>("Deleted PaymentType By ID: {}", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody PaymentType paymentType){
        paymentTypeService.delete(paymentType);
        return new ResponseEntity<>("Delete PaymentType:", HttpStatus.OK);
    }


}
