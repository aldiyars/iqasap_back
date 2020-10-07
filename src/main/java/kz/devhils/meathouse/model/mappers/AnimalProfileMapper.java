package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.dtos.request.AnimalProfileReq;
import kz.devhils.meathouse.model.dtos.response.AnimalProfileRes;

public interface AnimalProfileMapper {

    AnimalProfile toEntity(AnimalProfileReq animalProfileReq);
    AnimalProfileRes toDto(AnimalProfile animalProfile);
}
