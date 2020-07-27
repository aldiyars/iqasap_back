package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.entities.Roles;
import kz.devhils.meathouse.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/role")
@AllArgsConstructor
public class RoleRestController {

    private RoleService roleService;

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Roles result = roleService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Roles> result = roleService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addRole(@RequestBody Roles role){
        Roles result = roleService.save(role);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateRole(@RequestBody Roles role){
        Roles result = roleService.updateRole(role);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestBody Roles role){
        roleService.delete(role);
        return new ResponseEntity<>("Deleted Role", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        roleService.deleteById(id);
        return new ResponseEntity<>("Deleted Role By ID: {}", HttpStatus.OK);
    }

}
