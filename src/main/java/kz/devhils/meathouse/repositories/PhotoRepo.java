package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
}
