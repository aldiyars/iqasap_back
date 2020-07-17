package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Roles, Long>{
    Roles findByName(String name);
}
