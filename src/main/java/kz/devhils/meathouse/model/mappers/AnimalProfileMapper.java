package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.AnimalProfileDto;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimalProfileMapper {

    AnimalProfileMapper INSTANCE = Mappers.getMapper(AnimalProfileMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    AnimalProfileDto animalProfileToDTO(AnimalProfile animalProfile);
}
