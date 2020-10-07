package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//@EnableJpaRepositories
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByTel(Long tel);
}
