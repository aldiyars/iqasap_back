package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.request.UpdateUserRequest;
import kz.devhils.meathouse.model.dtos.response.UserResponse;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.mappers.UpdateUserMapper;
import kz.devhils.meathouse.repositories.UserProfileRepo;
import kz.devhils.meathouse.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserMapperImpl implements UpdateUserMapper {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserProfileRepo userProfileRepo;

    @Override
    public User toEntity(UpdateUserRequest updateUserRequest) {
        User user = userRepo.getOne(updateUserRequest.getId());

        UserProfile userProfile = userProfileRepo.getOne(user.getUserProfile().getId());
        userProfile.setAddress(updateUserRequest.getAddress());
        userProfileRepo.save(userProfile);

        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setEmail(updateUserRequest.getEmail());
        user.setTel(updateUserRequest.getTel());

        return user;
    }

//    @Override
//    public UserResponse toDto(User user) {
//        UserResponse result = new UserResponse();
//        result.setEmail(user.getEmail());
//        result.setTel(user.getTel());
//        result.setFirstName(user.getFirstName());
//        result.setLastName(user.getLastName());
//        result.setAddress(user.getUserProfile().getAddress());
//
//        return result;
//    }
}
