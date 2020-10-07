package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.UpdateUserDto;
import kz.devhils.meathouse.model.entities.Users;

public interface UserMapper {

    Users toEntity(UpdateUserDto updateUserDto);
    UpdateUserDto toDto(Users animalProfile);
}
