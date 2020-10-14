package kz.devhils.meathouse.service;


import kz.devhils.meathouse.model.entities.Role;

import java.util.List;

public interface RoleService {

    Role findById(Long id);
    List<Role> getAll();
    Role save(Role role);
    Role updateRole(Role role);
    void delete(Role role);
    void deleteById(Long id);
}
