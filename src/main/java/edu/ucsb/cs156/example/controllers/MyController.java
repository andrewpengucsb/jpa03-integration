package edu.ucsb.cs156.example.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.annotation.Profile;

@Controller
public class MyController {

    @GetMapping("/")
    public String greeting(OAuth2AuthenticationToken authentication,
                            Model model) {

            String name = authentication.getPrincipal().getAttributes().get("name").toString();

            model.addAttribute("name", name);

            return "greeting";
    }
}
