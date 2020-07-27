package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Statuses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Statuses, Long> {

    Statuses findByName(String name);
}
