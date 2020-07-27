package kz.devhils.meathouse.service;


import kz.devhils.meathouse.model.entities.Roles;

import java.util.List;

public interface RoleService {

    Roles findById(Long id);
    List<Roles> getAll();
    Roles save(Roles role);
    Roles updateRole(Roles role);
    void delete(Roles role);
    void deleteById(Long id);
}
