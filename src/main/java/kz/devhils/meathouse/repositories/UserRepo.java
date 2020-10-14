package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByTel(Long tel);
}
