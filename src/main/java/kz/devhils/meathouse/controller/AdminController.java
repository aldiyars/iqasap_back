package kz.devhils.meathouse.controller;

import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.Roles;
import kz.devhils.meathouse.model.entities.Users;
import kz.devhils.meathouse.repositories.AnimalRepo;
import kz.devhils.meathouse.repositories.PhotoRepo;
import kz.devhils.meathouse.repositories.RoleRepo;
import kz.devhils.meathouse.repositories.UserRepo;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("cms")
public class AdminController {
//
//    @Autowired
//    UserRepo userRepository;
//    @Autowired
//    AnimalRepo animalTypeRepo;
//    @Autowired
//    RoleRepo roleRepo;
//    @Autowired
//    PhotoRepo photoRepo;
//    @Value("${upload.path}")
//    private String uploadPath;
//
//    @GetMapping(path = "")
//    public String cms(Model model) {
//        model.addAttribute("currentUser", getUserData().getFirstName());
//        return "test";
//    }
//
//    public Users getUserData() {
//        Users userData = null;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            User secUser = (User) authentication.getPrincipal();
//            userData = userRepository.findByEmail(secUser.getUsername());
//        }
//        return userData;
//    }
//
//    @GetMapping(path = "/category")
//    public String category(Model model) {
//        List<Roles> roles = roleRepo.findAll();
//        model.addAttribute("roles", roles);
//        return "category";
//    }
//
//    @PostMapping(path = "/createCategory")
//    public String AddCategory(@RequestParam(name = "name") String name,
//                              @RequestParam(name = "file") MultipartFile file) throws IOException {
//        Animal animalType = animalTypeRepo.findAnimalByName(name);
//
//        if (animalType == null) {
//            if (file != null) {
//                File uploadDir = new File(uploadPath);
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//                String uuidFile = UUID.randomUUID().toString();
//                String resFileName = uuidFile + "." + file.getOriginalFilename();
//                file.transferTo(new File(uploadPath + "/" + resFileName));
//
//                Photo photo = new Photo(null, resFileName, "", null);
//                photoRepo.save(photo);
//
////                animalType = new Animal(null, name, photo);
////                animalTypeRepo.save(animalType);
//                return "redirect:/cms/category?success";
//            }
//        }
//
//        return "redirect:/cms/category?error";
//
//    }
}
