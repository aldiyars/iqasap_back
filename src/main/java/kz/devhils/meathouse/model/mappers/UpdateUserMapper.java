package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.request.UpdateUserRequest;
import kz.devhils.meathouse.model.entities.User;

public interface UpdateUserMapper {

    User toEntity(UpdateUserRequest updateUserRequest);
//    UserResponse toDto(User user);
}
