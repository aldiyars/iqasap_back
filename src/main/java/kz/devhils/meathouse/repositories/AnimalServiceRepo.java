package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.AnimalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalServiceRepo extends JpaRepository<AnimalService, Long> {
}
