package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.UpdateUserDto;
import kz.devhils.meathouse.model.dtos.response.UserResponse;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.mappers.UserMapper;
import kz.devhils.meathouse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Autowired
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
    public UserResponse toDto(User user) {
        UserResponse result = new UserResponse();
        result.setId(user.getId());
        result.setEmail(user.getEmail());
        result.setTel(user.getTel());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setAddress(user.getUserProfile().getAddress());

        if (user.getUserProfile().getPhoto() != null){
            result.setPhotoUrl(user.getUserProfile().getPhoto().getFileUrl());
        }else {
            result.setPhotoUrl(null);
        }

        return result;
    }
}
