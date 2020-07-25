package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.entities.Users;

import java.util.List;

//@Service
public interface UserService {

    Users register(Users user);

    List<Users> getAll();

    Users findByEmail(String email);

    Users findById(Long id);

    void delete(Long id);

    UserProfile registerProfile(UserProfile userProfile);

    void deleteUserProfile(Long id);
}
