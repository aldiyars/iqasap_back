package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.response.AnimalResponse;
import kz.devhils.meathouse.model.entities.Animal;

public interface AnimalMapper {

    AnimalResponse toDto (Animal animal);
}
