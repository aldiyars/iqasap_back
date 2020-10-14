package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Status;

import java.util.List;

public interface AnimalService {

    Animal saveAnimal(Animal animal);

    List<Animal> getAnimals();

    Animal findById(Long id);

    Animal updateAnimal(Animal animal);

    void deleteAnimalById(Long id);

    void deleteAnimal(Animal animal);


    //Children

    List<AnimalProfile> getAllProfiles();

    List<AnimalProfile> getAllByAnimalId(Long id);

    AnimalProfile saveProfile(AnimalProfile animalProfile);

    AnimalProfile getProfileById(Long id);

    AnimalProfile updateProfile(AnimalProfile animalProfile);

    AnimalProfile updateStatus(AnimalProfile animalProfile, Status status);

    void deleteByIdProfile(Long id);

    void deleteProfile(AnimalProfile profile);

}
