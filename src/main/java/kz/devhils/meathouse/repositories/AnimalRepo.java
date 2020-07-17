package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepo extends JpaRepository<Animal, Long> {
    Animal getOne(Long id);

//    List<Animal> findAllByDeletedAtNullOrderByIdAsc();
}
