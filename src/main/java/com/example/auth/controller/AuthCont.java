package com.example.auth.controller;

import com.example.auth.Repos.SiteUrepo;
import com.example.auth.model.SiteUser;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthCont {
    @Autowired
    SiteUrepo repo;
    @GetMapping("/login")
    public String getLoginPage(){
        return "/login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "/signup.html";
    }
    @GetMapping("/")
    public String h(){
        return "/index.html";
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password){

//        String plainTextPassword=password;
        //SiteUser siteUser = new SiteUser(username,password);
        String hashedPassword= BCrypt.hashpw(password, BCrypt.gensalt(12));
        SiteUser siteUser = new SiteUser(username,hashedPassword);

        repo.save(siteUser);
        return new RedirectView("login");
    }
    @PostMapping("/login")
    public RedirectView logInUser(String username, String password){
        SiteUser userFromDb=repo.findByUsername(username);
//        if((userFromDb == null)
//           || !(userFromDb.getPassword().equals(password))){
//           return new RedirectView("/login");
//       }
        if((userFromDb == null)
                || (!BCrypt.checkpw(password, userFromDb.getPassword())))
        {
            return new RedirectView("/login");
        }
        return new RedirectView("/");
}}
