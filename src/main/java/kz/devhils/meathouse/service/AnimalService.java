package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.Animal;
import kz.devhils.meathouse.model.AnimalProfile;
import kz.devhils.meathouse.model.Statuses;

import java.util.List;

public interface AnimalService {

    Animal animalRegister(Animal animal);

    List<Animal> getAnimals();

    Animal findById(Long id);

    Animal animalUpdate(Animal animal);

    void animalDelete(Long id);

    List<AnimalProfile> getAll();

    AnimalProfile animalProfileRegister(AnimalProfile animalProfile);

    AnimalProfile getOne(Long id);

    AnimalProfile updateAnimalProfile(AnimalProfile animalProfile);

    AnimalProfile setStatus(Statuses status);

    void animalProfileDelete(Long id);


}
