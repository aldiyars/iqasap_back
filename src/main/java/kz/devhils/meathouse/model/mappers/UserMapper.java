package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.UpdateUserDto;
import kz.devhils.meathouse.model.entities.User;

public interface UserMapper {

    User toEntity(UpdateUserDto updateUserDto);
    UpdateUserDto toDto(User animalProfile);
}
