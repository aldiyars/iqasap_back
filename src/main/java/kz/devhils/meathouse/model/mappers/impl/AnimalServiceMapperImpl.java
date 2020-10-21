package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.request.AnimalServiceRequest;
import kz.devhils.meathouse.model.dtos.request.CreateAnimalServiceRequest;
import kz.devhils.meathouse.model.dtos.response.AnimalServiceResponse;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalService;
import kz.devhils.meathouse.model.mappers.AnimalServiceMapper;
import kz.devhils.meathouse.service.AnimalServiceService;
import kz.devhils.meathouse.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceMapperImpl implements AnimalServiceMapper {

    @Autowired
    private kz.devhils.meathouse.service.AnimalService animalService;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private AnimalServiceService animalServiceService;

    @Override
    public AnimalService toEntity(AnimalServiceRequest animalServiceRequest) {
        kz.devhils.meathouse.model.entities.Service service = servicesService.findById(animalServiceRequest.getServiceId());
        Animal animal = animalService.findById(animalServiceRequest.getAnimalId());
        AnimalService animalService = animalServiceService.findById(animalServiceRequest.getAnimalId());
        animalService.setAnimal(animal);
        animalService.setService(service);
        animalService.setCost(animalServiceRequest.getCost());
        return animalService;
    }

    @Override
    public AnimalServiceResponse toDto(AnimalService animalService) {
        AnimalServiceResponse animalServiceResponse = new AnimalServiceResponse();
        animalServiceResponse.setId(animalService.getId());
        animalServiceResponse.setCost(animalService.getCost());
        animalServiceResponse.setAnimalName(animalService.getAnimal().getName());
        animalServiceResponse.setServiceName(animalService.getService().getName());

        return animalServiceResponse;
    }

    @Override
    public AnimalService inCreateEntityToEntity(CreateAnimalServiceRequest createAnimalServiceRequest) {
        kz.devhils.meathouse.model.entities.Service service = servicesService.findById(createAnimalServiceRequest.getServiceId());
        Animal animal = animalService.findById(createAnimalServiceRequest.getAnimalId());

        AnimalService animalService = new AnimalService();
        animalService.setAnimal(animal);
        animalService.setService(service);
        animalService.setCost(createAnimalServiceRequest.getCost());
        return animalService;
    }
}
