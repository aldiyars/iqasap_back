package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.entities.Services;
import kz.devhils.meathouse.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "rest/v1/services")
public class ServiceRestController {

    private ServicesService servicesService;

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Services result = servicesService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Services> result = servicesService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addService(@RequestBody Services service){
        Services result = servicesService.save(service);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateService(@RequestBody Services service){
        Services result = servicesService.update(service);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody Services service){
        servicesService.delete(service);
        return new ResponseEntity<>("Deleted Status", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        servicesService.deleteById(id);
        return new ResponseEntity<>("Deleted Status by ID: {}", HttpStatus.OK);
    }
}
