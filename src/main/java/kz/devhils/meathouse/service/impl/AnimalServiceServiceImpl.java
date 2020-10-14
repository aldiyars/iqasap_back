package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.AnimalService;
import kz.devhils.meathouse.repositories.AnimalServiceRepo;
import kz.devhils.meathouse.service.AnimalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceServiceImpl implements AnimalServiceService {

    @Autowired
    private AnimalServiceRepo animalServiceRepo;

    @Override
    public AnimalService findById(Long id) {
        AnimalService result = animalServiceRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<AnimalService> getAll() {
        List<AnimalService> result = animalServiceRepo.findAll();
        return result;
    }

    @Override
    public AnimalService add(AnimalService service) {
        AnimalService result = animalServiceRepo.save(service);
        return result;
    }

    @Override
    public AnimalService update(AnimalService animalService) {
        if (animalService.getId() != null && animalServiceRepo.getOne(animalService.getId()) !=null){
            animalService = animalServiceRepo.save(animalService);
        }
        return animalService;
    }

    @Override
    public void deleteById(Long id) {
        animalServiceRepo.deleteById(id);
    }

}
