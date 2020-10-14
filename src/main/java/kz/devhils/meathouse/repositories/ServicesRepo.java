package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepo extends JpaRepository<Service, Long> {
}
