package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.UpdateUserDto;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.mappers.UserMapper;
import kz.devhils.meathouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserMapperImpl implements UserMapper {

    private UserService userService;

    @Override
    public User toEntity(UpdateUserDto updateUserDto) {

        User result = userService.findById(updateUserDto.getId());

        result.setLastName(updateUserDto.getLastName());
        result.setFirstName(updateUserDto.getFirstName());
        result.setTel(updateUserDto.getTel());
        result.setEmail(updateUserDto.getEmail());
        result.setRoles(updateUserDto.getRoles());
        result.setUserProfile(updateUserDto.getProfile());

        return result;
    }

    @Override
    public UpdateUserDto toDto(User animalProfile) {
        return null;
    }
}
