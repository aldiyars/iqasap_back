package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Roles;
import kz.devhils.meathouse.model.entities.UserProfile;
import kz.devhils.meathouse.model.entities.Users;
import kz.devhils.meathouse.repositories.RoleRepo;
import kz.devhils.meathouse.repositories.UserProfileRepo;
import kz.devhils.meathouse.repositories.UserRepo;
import kz.devhils.meathouse.service.UserService;
import lombok.AllArgsConstructor;
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
    public Users register(Users user) {
        Roles roleUser = roleRepo.findByName("ROLE_USER");
        List<Roles> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        Users registeredUser = userRepo.save(user);

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
    public Users authMobile(Users users) {
        Roles role = roleRepo.findByName("ROLE_USER");
        List<Roles> uRoles = new ArrayList<>();
        uRoles.add(role);
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        users.setRoles(uRoles);
        Users result = userRepo.save(users);

        return result;
    }

    @Override
    public Users findByTel(Long tel) {
        Users result = userRepo.findByTel(tel);
        log.info("IN findByEmail - {} user found by phone: {}", result, tel);

        return userRepo.findByTel(tel);
    }

    @Override
    public List<Users> getAll() {
        List<Users> result = userRepo.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public Users findByEmail(String email) {
        Users result = userRepo.findByTel(Long.parseLong(email));
        log.info("IN findByEmail - {} user found by email: {}", result, email);
        return result;
    }

    @Override
    public Users findById(Long id) {
        Users result = userRepo.findById(id).orElse(null);

        if(result == null){
            log.warn("IN findById - no user found by id: {}", id);
        }
        log.info("IN findById  - {} user found by id: {}", result, id);
        return result;
    }

    @Override
    public Users updateUser(Users user) {
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
    public void delete(Users user) {
        userRepo.delete(user);
        log.info("IN delete - user with user: {} successfully deleted");
    }
}
