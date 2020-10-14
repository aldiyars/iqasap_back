package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;

import java.util.List;

public interface UserService {

    User register(User user);
    List<User> getAll();
    User findByEmail(String email);
    User findById(Long id);
    User updateUser(User user);
    void delete(Long id);
    void delete(User user);
    UserProfile registerProfile(UserProfile userProfile);
    void deleteUserProfile(Long id);
    User authMobile(User user);
    User findByTel(Long tel);
}
