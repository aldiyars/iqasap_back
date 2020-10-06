package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.UpdateUserDto;
import kz.devhils.meathouse.model.entities.Users;
import kz.devhils.meathouse.model.mappers.UserMapper;
import kz.devhils.meathouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {

    private UserService userService;

    @Override
    public Users toEntity(UpdateUserDto updateUserDto) {
        Users result = userService.findById(updateUserDto.getId());

        result.setLastName(updateUserDto.getLastName());
        result.setFirstName(updateUserDto.getFirstName());
        result.setTel(updateUserDto.getTel());
        result.setEmail(updateUserDto.getEmail());
        result.setRoles(updateUserDto.getRoles());
        result.setUserProfile(updateUserDto.getProfile());

        return result;
    }

    @Override
    public UpdateUserDto toDto(Users animalProfile) {
        return null;
    }
}
