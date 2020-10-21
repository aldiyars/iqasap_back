package kz.devhils.meathouse.model.mappers;


import kz.devhils.meathouse.model.dtos.request.AnimalServiceRequest;
import kz.devhils.meathouse.model.dtos.request.CreateAnimalServiceRequest;
import kz.devhils.meathouse.model.dtos.response.AnimalServiceResponse;
import kz.devhils.meathouse.model.entities.AnimalService;

public interface AnimalServiceMapper {

    AnimalService toEntity(AnimalServiceRequest animalServiceRequest);
    AnimalServiceResponse toDto (AnimalService animalService);
    AnimalService inCreateEntityToEntity (CreateAnimalServiceRequest createAnimalServiceRequest);
}
