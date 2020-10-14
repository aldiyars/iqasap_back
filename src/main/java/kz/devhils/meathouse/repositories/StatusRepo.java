package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Long> {

    Status findByName(String name);
}
