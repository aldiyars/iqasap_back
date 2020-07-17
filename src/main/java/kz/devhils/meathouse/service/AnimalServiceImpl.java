package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.Animal;
import kz.devhils.meathouse.model.AnimalProfile;
import kz.devhils.meathouse.model.Statuses;
import kz.devhils.meathouse.repositories.AnimalProfileRepo;
import kz.devhils.meathouse.repositories.AnimalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepo animalRepo;
    private final AnimalProfileRepo animalProfileRepo;

    public AnimalServiceImpl(AnimalRepo animalRepo, AnimalProfileRepo animalProfileRepo) {
        this.animalRepo = animalRepo;
        this.animalProfileRepo = animalProfileRepo;
    }

    @Override
    public Animal animalRegister(Animal animal) {
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
        Animal result = animalRepo.getOne(id);
        return result;
    }

    @Override
    public Animal animalUpdate(Animal animal) {
        Animal result = animalRepo.getOne(animal.getId());

        result.setIconUrl(animal.getIconUrl());
        result.setName(animal.getName());

        return result;
    }

    @Override
    public void animalDelete(Long id) {
        animalRepo.deleteById(id);
    }

    @Override
    public List<AnimalProfile> getAll() {
        List<AnimalProfile> result = animalProfileRepo.findAll();
        return result;
    }

    @Override
    public AnimalProfile animalProfileRegister(AnimalProfile animalProfile) {
        AnimalProfile result = animalProfileRepo.save(animalProfile);
        return result;
    }

    @Override
    public AnimalProfile getOne(Long id) {
        AnimalProfile result = animalProfileRepo.getOne(id);
        return result;
    }

    @Override
    public AnimalProfile updateAnimalProfile(AnimalProfile animalProfile) {
        AnimalProfile result = animalProfileRepo.getOne(animalProfile.getId());

        result.setAge(animalProfile.getAge());
        result.setBreed(animalProfile.getBreed());
        result.setColor(animalProfile.getColor());
        result.setCost(animalProfile.getCost());
        result.setGender(animalProfile.getGender());
        result.setPhotos(animalProfile.getPhotos());

        animalProfileRepo.save(result);
        return result;
    }

    @Override
    public AnimalProfile setStatus(Statuses status) {
        return null;
    }

    @Override
    public void animalProfileDelete(Long id) {

    }
}
