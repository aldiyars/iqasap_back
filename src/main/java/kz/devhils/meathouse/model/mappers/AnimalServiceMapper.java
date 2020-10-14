package kz.devhils.meathouse.model.mappers;


import kz.devhils.meathouse.model.dtos.request.CreateAnimalService;
import kz.devhils.meathouse.model.entities.AnimalService;

public interface AnimalServiceMapper {

    AnimalService toEntity(CreateAnimalService createAnimalService);
}
