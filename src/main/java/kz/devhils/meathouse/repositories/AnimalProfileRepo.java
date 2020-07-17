package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.AnimalProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalProfileRepo extends JpaRepository<AnimalProfile, Long> {
//
//    List<AnimalProfile> findByAllAnimal(Animal animal);
//    List<AnimalProfile> findByAnimalId(Long id);

//    Page<AnimalProfile> findAllByAnimal(Pageable p);


}

