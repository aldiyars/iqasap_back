package kz.devhils.meathouse.controller.rest;


import kz.devhils.meathouse.model.entities.Statuses;
import kz.devhils.meathouse.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/status")
public class StatusRestController {

    private StatusService statusService;

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Statuses result = statusService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Statuses> result = statusService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addStatus(@RequestBody Statuses status){
        Statuses result = statusService.addStatus(status);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateStatus(@RequestBody Statuses status){
        Statuses result = statusService.update(status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        statusService.deleteById(id);
        return new ResponseEntity<>("Deleted Status by ID: {}", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteStatus(@RequestBody Statuses status){
        statusService.delete(status);
        return new ResponseEntity<>("Deleted Status: {}", HttpStatus.OK);
    }
}
