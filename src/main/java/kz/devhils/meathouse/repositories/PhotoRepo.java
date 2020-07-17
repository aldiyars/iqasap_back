package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo, Long> {
}
