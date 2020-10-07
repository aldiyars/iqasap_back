package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.entities.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    Users register(Users user);

    List<Users> getAll();

    Users findByEmail(String email);

    Users findById(Long id);

    Users updateUser(Users user);

    void delete(Long id);

    void delete(Users user);

    UserProfile registerProfile(UserProfile userProfile);

    void deleteUserProfile(Long id);

    Users authMobile(Users users);

    Users findByTel(Long tel);
}
