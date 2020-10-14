package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.repositories.AnimalProfileRepo;
import kz.devhils.meathouse.repositories.AnimalRepo;
import kz.devhils.meathouse.repositories.StatusRepo;
import kz.devhils.meathouse.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepo animalRepo;
    @Autowired
    private AnimalProfileRepo animalProfileRepo;
    @Autowired
    private StatusRepo statusRepo;

    @Override
    public Animal saveAnimal(Animal animal) {
        Animal result = animalRepo.save(animal);
        log.info("IN register = animal: {} successfully registered", result);
        return result;
    }

    @Override
    public List<Animal> getAnimals() {
        List<Animal> result = animalRepo.findAll();
        return result;
    }

    @Override
    public Animal findById(Long id) {
        Animal result = animalRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        if(animal.getId() != null && animalRepo.getOne(animal.getId()) != null){
            animal = animalRepo.save(animal);
        }
        return animal;
    }

    @Override
    public void deleteAnimalById(Long id) {
        animalRepo.deleteById(id);
    }

    @Override
    public void deleteAnimal(Animal animal) {
        animalRepo.delete(animal);
    }

    @Override
    public List<AnimalProfile> getAllProfiles() {
        List<AnimalProfile> result = animalProfileRepo.findAll();
        return result;
    }

    @Override
    public List<AnimalProfile> getAllByAnimalId(Long id) {
        List<AnimalProfile> result = animalProfileRepo.findAllByAnimal_Id(id);
        return result;
    }

    @Override
    public AnimalProfile saveProfile(AnimalProfile animalProfile) {
        Status status = statusRepo.findByName("active");
        animalProfile.setStatus(status);
        AnimalProfile result = animalProfileRepo.save(animalProfile);
        return result;
    }

    @Override
    public AnimalProfile getProfileById(Long id) {
        AnimalProfile result = animalProfileRepo.getOne(id);
        return result;
    }

    @Override
    public AnimalProfile updateProfile(AnimalProfile animalProfile) {
        AnimalProfile result = animalProfileRepo.getOne(animalProfile.getId());
        animalProfileRepo.save(result);
        return result;
    }

    @Override
    public AnimalProfile updateStatus(AnimalProfile animalProfile, Status status) {
        AnimalProfile result = animalProfileRepo.getOne(animalProfile.getId());
        result.setStatus(status);
        animalProfileRepo.save(result);
        return result;
    }

    @Override
    public void deleteByIdProfile(Long id) {
        animalProfileRepo.deleteById(id);
    }

    @Override
    public void deleteProfile(AnimalProfile profile) {
        animalProfileRepo.delete(profile);
    }
}
