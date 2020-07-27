package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Roles;
import kz.devhils.meathouse.repositories.RoleRepo;
import kz.devhils.meathouse.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {

    private RoleRepo roleRepo;

    @Override
    public Roles findById(Long id) {
        Roles result = roleRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<Roles> getAll() {
        List<Roles> result = roleRepo.findAll();
        return result;
    }

    @Override
    public Roles save(Roles role) {
        Roles result = roleRepo.save(role);
        return result;
    }

    @Override
    public Roles updateRole(Roles role) {
        if (role.getId() != null && findById(role.getId()) != null){
            roleRepo.save(role);
        }
        return role;
    }

    @Override
    public void delete(Roles role) {
        roleRepo.delete(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepo.deleteById(id);
    }
}
