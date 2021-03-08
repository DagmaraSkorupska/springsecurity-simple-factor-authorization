package pl.skorupska.springsecuritysimplefactorauthorization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.skorupska.springsecuritysimplefactorauthorization.exception.TokenNotFoundException;
import pl.skorupska.springsecuritysimplefactorauthorization.exception.UserNotExistException;
import pl.skorupska.springsecuritysimplefactorauthorization.model.Token;
import pl.skorupska.springsecuritysimplefactorauthorization.model.UserApp;
import pl.skorupska.springsecuritysimplefactorauthorization.repo.TokenRepo;
import pl.skorupska.springsecuritysimplefactorauthorization.repo.UserAppRepo;
import pl.skorupska.springsecuritysimplefactorauthorization.service.UserService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {


    private UserService userService;
    private TokenRepo tokenRepo;
    private UserAppRepo userAppRepo;

    public UserController(UserService userService, TokenRepo tokenRepo, UserAppRepo userAppRepo) {
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.userAppRepo = userAppRepo;
    }

    //    @GetMapping("/hello")
//    @ResponseBody
//    public String hello(){
//        return "hello";
//    }


    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("name", principal.getName());
        Collection<? extends GrantedAuthority> authorities= SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("authorities", authorities);
        return "hello";
    }

    @GetMapping("/sing-up")
    public String singup(Model model){
        model.addAttribute("user", new UserApp());
        return "sing-up";
    }

    @PostMapping("/register")
    public String register(UserApp userApp){
        userService.addUser(userApp);
        return "sing-up";
    }

    @GetMapping("/token")
    public String token(@RequestParam String value) throws TokenNotFoundException {
        Token byValue =  tokenRepo.findByValue(value).orElseThrow(()-> new TokenNotFoundException(value));
        UserApp userApp = byValue.getUserApp();
        userApp.setEnabled(true);
        userAppRepo.save(userApp);
        return "hello";
    }
}
