package com.example.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    private String getPrincipal(){
        
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping(value = {"","/home"})
    String homePage (Model model){
        model.addAttribute("user",getPrincipal());
        return "welcome";
    }

    @GetMapping("/admin")
    String adminPage(Model model){
        model.addAttribute("user",getPrincipal());
        return "admin";
    }
    @GetMapping("/Access_Denied")
    String accessDeniedPage(Model model){
        model.addAttribute("user",getPrincipal());
        return "accessDenied";
    }
    @GetMapping("/dba")
    public String dbaPage(Model model) {
        model.addAttribute("user",getPrincipal());
        return "dba";
    }
    @GetMapping("/login")
    public String login (){
        return "index";
    }
}
