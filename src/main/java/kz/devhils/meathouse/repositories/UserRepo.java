package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
