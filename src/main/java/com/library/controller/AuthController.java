package com.library.controller;

import com.library.model.User;
import com.library.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String role) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        userRepository.save(user);

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        User user = userRepository
                .findByUsernameAndPassword(username, password)
                .orElse(null);

        if (user != null) {
            session.setAttribute("user", user);

            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                return "redirect:/admin";
            } else {
                return "redirect:/student";
            }
        }

        return "redirect:/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}