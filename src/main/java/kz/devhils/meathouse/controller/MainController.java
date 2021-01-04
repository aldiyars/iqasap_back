package kz.devhils.meathouse.controller;

import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//import javax.jws.WebParam;

@Controller
public class MainController {

//    @Autowired
//    UserRepo userRepo;
//
//    @GetMapping(path = "/cms")
//    public String cms(Model model){
//        model.addAttribute("currentUser", getUserData().getFirstName());
//        return "test";
//    }
//
//    public User getUserData(){
//        User userData = null;
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!(authentication instanceof AnonymousAuthenticationToken)){
//            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
//            userData = userRepo.findByEmail(secUser.getUsername());
//        }
//        return userData;
//    }
}
