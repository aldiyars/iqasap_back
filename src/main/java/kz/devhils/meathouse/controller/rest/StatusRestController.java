package kz.devhils.meathouse.controller.rest;


import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/status")
public class StatusRestController {

    @Autowired
    private StatusService statusService;

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Status result = statusService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Status> result = statusService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("{status}")
    public ResponseEntity<?> addStatus(@PathVariable String status){
        Status statuses = new Status();
        statuses.setName(status);
        Status result = statusService.addStatus(statuses);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateStatus(@RequestBody Status status){
        Status result = statusService.update(status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        statusService.deleteById(id);
        return new ResponseEntity<>("Deleted Status by ID: {}", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteStatus(@RequestBody Status status){
        statusService.delete(status);
        return new ResponseEntity<>("Deleted Status: {}", HttpStatus.OK);
    }
}
