package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Role;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.repositories.RoleRepo;
import kz.devhils.meathouse.repositories.UserProfileRepo;
import kz.devhils.meathouse.repositories.UserRepo;
import kz.devhils.meathouse.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserProfileRepo userProfileRepo;

    @Override
    public User register(User user) {
        Role roleUser = roleRepo.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        User registeredUser = userRepo.save(user);

        log.info("IN register = user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public UserProfile registerProfile(UserProfile userProfile) {
        UserProfile result = userProfileRepo.save(userProfile);
        log.info("IN register = userProfile: {} successfully registered", result);
        return result;
    }

    @Override
    public void deleteUserProfile(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User authMobile(User user) {
        Role role = roleRepo.findByName("ROLE_USER");
        List<Role> uRoles = new ArrayList<>();
        uRoles.add(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(uRoles);
        User result = userRepo.save(user);

        return result;
    }

    @Override
    public User findByTel(Long tel) {
        User result = userRepo.findByTel(tel);
        log.info("IN findByEmail - {} user found by phone: {}", result, tel);

        return userRepo.findByTel(tel);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepo.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepo.findByTel(Long.parseLong(email));
        log.info("IN findByEmail - {} user found by email: {}", result, email);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepo.findById(id).orElse(null);

        if(result == null){
            log.warn("IN findById - no user found by id: {}", id);
        }
        log.info("IN findById  - {} user found by id: {}", result, id);
        return result;
    }

    @Override
    public User updateUser(User user) {
        if(user.getId() != null && userRepo.findById(user.getId()).orElse(null) != null){
            userRepo.save(user);
            log.info("IN updated - user with user: {} successfully updated");
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
        log.info("IN delete - user with user: {} successfully deleted");
    }
}
