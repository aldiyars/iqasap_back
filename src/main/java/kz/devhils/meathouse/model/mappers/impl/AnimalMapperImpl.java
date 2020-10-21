package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.response.AnimalResponse;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.mappers.AnimalMapper;
import org.springframework.stereotype.Service;

@Service
public class AnimalMapperImpl implements AnimalMapper {

    @Override
    public AnimalResponse toDto(Animal animal) {
        AnimalResponse animalResponse = new AnimalResponse();
        animalResponse.setId(animal.getId());
        animalResponse.setName(animal.getName());
        animalResponse.setPhotoUrl(animal.getPhoto().getFileUrl());
        return animalResponse;
    }
}
