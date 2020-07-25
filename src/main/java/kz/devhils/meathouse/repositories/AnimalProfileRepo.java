package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.AnimalProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalProfileRepo extends JpaRepository<AnimalProfile, Long> {

//    @Query(value = "SELECT * from animal_profile a WHERE a.animal_id = ?1", nativeQuery = true)
//    List<AnimalProfile> findByAnimalId(Long id);

    List<AnimalProfile> findAllByAnimal_Id(Long id);

}

