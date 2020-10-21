package kz.devhils.meathouse.controller.rest;

import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.response.ServiceResponse;
import kz.devhils.meathouse.model.entities.Service;
import kz.devhils.meathouse.model.mappers.ServiceMapper;
import kz.devhils.meathouse.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/service")
public class ServiceRestController {

    @Autowired
    private ServicesService servicesService;
    @Autowired
    private ServiceMapper serviceMapper;

    @GetMapping("{id}")
    @ApiOperation("Получение Service по ID")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Service result = servicesService.findById(id);
        return new ResponseEntity<>(serviceMapper.toDto(result), HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation("Получение вес список Service")
    public ResponseEntity<?> getAll(){
        List<Service> services = servicesService.getAll();

        List<ServiceResponse> serviceResponses = services.stream().map(e ->serviceMapper.toDto(e)).collect(Collectors.toList());
        return new ResponseEntity<>(serviceResponses, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation("Добавление Service")
    public ResponseEntity<?> addService(@RequestBody Service service){
        Service result = servicesService.save(service);
        return new ResponseEntity<>(serviceMapper.toDto(result), HttpStatus.CREATED);
    }

    @PutMapping()
    @ApiOperation("Обнавление Service")
    public ResponseEntity<?> updateService(@RequestBody Service service){
        Service result = servicesService.update(service);
        return new ResponseEntity<>(serviceMapper.toDto(result), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление Service по ID")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        servicesService.deleteById(id);
        return new ResponseEntity<>("Deleted Service by ID: {}", HttpStatus.OK);
    }
}
