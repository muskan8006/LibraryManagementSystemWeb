package com.library.controller;

import com.library.model.User;
import com.library.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    // CHANGE PASSWORD PAGE

    @GetMapping("/change-password")
    public String changePasswordPage() {
        return "change-password";
    }

    // CHANGE PASSWORD LOGIC

    @PostMapping("/change-password")
    public String changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        // old password check
        if (!user.getPassword().equals(oldPassword)) {

            model.addAttribute("error", "Old Password Incorrect");

            return "change-password";
        }

        // update password
        user.setPassword(newPassword);

        userRepository.save(user);

        model.addAttribute("success", "Password Changed Successfully");

        return "change-password";
    }
    // DELETE USER

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Integer id) {

        userRepository.deleteById(id);

        return "redirect:/admin";
    }

}