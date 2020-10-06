package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.request.AnimalProfileReq;
import kz.devhils.meathouse.model.response.AnimalProfileRes;

public interface AnimalPrMapper {

    AnimalProfile toEntity(AnimalProfileReq animalProfileReq);
    AnimalProfileRes toDto(AnimalProfile animalProfile);
}
