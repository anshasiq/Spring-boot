package com.example.auth.controller;

import com.example.auth.Repos.PostsRepo;
import com.example.auth.Repos.SiteUrepo;
import com.example.auth.model.Posts;
import com.example.auth.model.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeControl {
@Autowired
    PostsRepo Repo;
    @Autowired
    SiteUrepo SS;
    @GetMapping("/withSecret")
    public String getHomePageWithSecret(HttpServletRequest request, Model m, Model model){

        HttpSession session = request.getSession();
        String username= session.getAttribute("username").toString();

        m.addAttribute("username", username);
        List<Posts> posts = Repo.findAll();
        model.addAttribute("posts", posts);
        return "indexWithSecret.html";
    }
    @PostMapping("/addpost")
    public RedirectView addpost(HttpServletRequest request , String content){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
//        session.setAttribute("username", username);
        SiteUser siteUser = SS.findByUsername(username);
        Posts newPost = new Posts(content, siteUser);
        Repo.save(newPost);
        return new RedirectView("/withSecret");
    }
//    @GetMapping("/with")
//    public String getAllPosts(Model model) {
//        List<Posts> posts = Repo.findAll();
//        model.addAttribute("posts", posts);
//        return "/withSecret";
//    }
}
