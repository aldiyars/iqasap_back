package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.request.CreateAnimalService;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalService;
import kz.devhils.meathouse.model.mappers.AnimalServiceMapper;
import kz.devhils.meathouse.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceMapperImpl implements AnimalServiceMapper {

    @Autowired
    private kz.devhils.meathouse.service.AnimalService animalService;
    @Autowired
    private ServicesService servicesService;

    @Override
    public AnimalService toEntity(CreateAnimalService createAnimalService) {
        kz.devhils.meathouse.model.entities.Service service = servicesService.findById(createAnimalService.getServiceId());
        Animal animal = animalService.findById(createAnimalService.getAnimalId());
        AnimalService animalService = new AnimalService();
        animalService.setAnimal(animal);
        animalService.setService(service);
        animalService.setCost(createAnimalService.getCost());
        return animalService;
    }
}
