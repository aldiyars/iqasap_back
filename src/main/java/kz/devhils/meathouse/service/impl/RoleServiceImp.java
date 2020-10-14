package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Role;
import kz.devhils.meathouse.repositories.RoleRepo;
import kz.devhils.meathouse.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Role findById(Long id) {
        Role result = roleRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<Role> getAll() {
        List<Role> result = roleRepo.findAll();
        return result;
    }

    @Override
    public Role save(Role role) {
        Role result = roleRepo.save(role);
        return result;
    }

    @Override
    public Role updateRole(Role role) {
        if (role.getId() != null && findById(role.getId()) != null){
            roleRepo.save(role);
        }
        return role;
    }

    @Override
    public void delete(Role role) {
        roleRepo.delete(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepo.deleteById(id);
    }
}
