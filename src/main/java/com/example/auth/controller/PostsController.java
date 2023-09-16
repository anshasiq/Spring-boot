package com.example.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostsController {

    @GetMapping("/posts")
    public String getPosts(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("username") != null) {
//            List<Post> posts = postService.getAllPosts();
//            model.addAttribute("posts", posts);
            model.addAttribute("name",request.getSession().getAttribute("username"));
            return "posts";
        }
        return "redirect:/login";
    }
}
