package kz.devhils.meathouse.controller;

import kz.devhils.meathouse.model.Users;
import kz.devhils.meathouse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;

//    @GetMapping(path = "/cms")
//    public String cms(Model model){
//        model.addAttribute("currentUser", getUserData().getFirstName());
//        return "test";
//    }

    public Users getUserData(){
        Users userData = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            userData = userRepository.findByEmail(secUser.getUsername());
        }
        return userData;
    }
}
