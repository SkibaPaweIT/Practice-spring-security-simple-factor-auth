package pl.skiba.springsecuritysimplefactorauth.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skiba.springsecuritysimplefactorauth.model.Token;
import pl.skiba.springsecuritysimplefactorauth.model.appUser;
import pl.skiba.springsecuritysimplefactorauth.repo.AppUserRepo;
import pl.skiba.springsecuritysimplefactorauth.repo.TokenRepo;
import pl.skiba.springsecuritysimplefactorauth.service.userService;


import java.security.Principal;
import java.util.Collection;

@Controller
public class userController {

    private pl.skiba.springsecuritysimplefactorauth.service.userService userService;
    private TokenRepo tokenRepo;
    private AppUserRepo appUserRepo;

    public userController(pl.skiba.springsecuritysimplefactorauth.service.userService userService, TokenRepo tokenRepo, AppUserRepo appUserRepo) {
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.appUserRepo = appUserRepo;
    }

//    @GetMapping("/hello")
//    @ResponseBody
//    public String get() {
//        return "Hello";
//    }

//    @GetMapping("/for-admin")
//    public String forAdmin() {
//        return "hello-admin";
//    }

    @GetMapping("/hello")
    public String forUser(Principal principal , Model model) {
        model.addAttribute("name" , principal.getName()); //principal gets info from security token tbh
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities(); //normalnie rola wyciągana HERE
        //Object details=SecurityContextHolder.getContext().getAuthentication().getDetails();//DETAILS IP , SESSION AND STUFF
        model.addAttribute("authorities", authorities);
        //sendToken();
        return "hello";
    }

    @GetMapping("/sign-up")
    public String signup(Model model){
        model.addAttribute("user" , new appUser());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(appUser appuser) {
        userService.addUser(appuser);
        return "sign-up";
    }

    @GetMapping("/token")
    public String tokenVerify(@RequestParam String value) {
         Token byValue = tokenRepo.findByValue(value);
         appUser appUser=byValue.getAppUser();
         appUser.setEnabled(true);
         appUserRepo.save(appUser);
         return "hello";
    }




}
