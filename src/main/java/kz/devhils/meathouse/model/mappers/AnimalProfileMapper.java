package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.dtos.request.AnimalProfileReq;
import kz.devhils.meathouse.model.dtos.response.AnimalProfileResponse;

public interface AnimalProfileMapper {
    AnimalProfile toEntity(AnimalProfileReq animalProfileReq);
    AnimalProfileResponse toDto(AnimalProfile animalProfile);
}
