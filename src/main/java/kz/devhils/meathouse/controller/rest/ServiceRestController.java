package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.entities.Service;
import kz.devhils.meathouse.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/service")
public class ServiceRestController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Service result = servicesService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Service> result = servicesService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addService(@RequestBody Service service){
        Service result = servicesService.save(service);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateService(@RequestBody Service service){
        Service result = servicesService.update(service);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody Service service){
        servicesService.delete(service);
        return new ResponseEntity<>("Deleted Service", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        servicesService.deleteById(id);
        return new ResponseEntity<>("Deleted Service by ID: {}", HttpStatus.OK);
    }
}
