package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.OrderAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAnimalRepo extends JpaRepository<OrderAnimal, Long> {
}
