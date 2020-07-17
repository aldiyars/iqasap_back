package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.Roles;
import kz.devhils.meathouse.model.UserProfile;
import kz.devhils.meathouse.model.Users;
import kz.devhils.meathouse.repositories.RoleRepo;
import kz.devhils.meathouse.repositories.UserProfileRepo;
import kz.devhils.meathouse.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl  implements UserService{

    private final UserRepository userRepository;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserProfileRepo userProfileRepo;

    public UserServiceImpl(UserRepository userRepository, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder, UserProfileRepo userProfileRepo) {
        this.userRepository = userRepository;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userProfileRepo = userProfileRepo;
    }

    @Override
    public Users register(Users user) {
        Roles roleUser = roleRepo.findByName("ROLE_USER");
        List<Roles> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        Users registeredUser = userRepository.save(user);

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

    }

    @Override
    public List<Users> getAll() {
        List<Users> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public Users findByEmail(String email) {
        Users result = userRepository.findByEmail(email);
        log.info("IN findByEmail - {} user found by email: {}", result, email);
        return result;
    }

    @Override
    public Users findById(Long id) {
        Users result = userRepository.findById(id).orElse(null);

        if(result == null){
            log.warn("IN findById - no user found by id: {}", id);
        }
        log.info("IN findById  - {} user found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}
