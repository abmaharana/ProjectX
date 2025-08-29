package com.example.app.controller;

import com.example.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) { this.userService = userService; }

    // Redirect root to /login
    @GetMapping("/")
    public String home() { return "redirect:/login"; }

    // Pages
    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/signup")
    public String signupPage() { return "signup"; }

    // Sign up (email optional to keep tests simple)
    @PostMapping("/signup")
    public String signup(@RequestParam(name = "username") String username,
                         @RequestParam(name = "password") String password,
                         @RequestParam(name = "email", required = false) String email,
                         Model model) {

        String finalEmail = (email == null || email.isBlank())
                ? username + "@example.com"
                : email;

        if (userService.signup(username, password, finalEmail)) {
            model.addAttribute("message", "Signup successful. Please login.");
            return "login";
        }

        model.addAttribute("error", "Username already exists");
        return "signup";
    }


    // Login → store username in session then redirect
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean ok = userService.login(username, password);
        if (ok) {
            return "redirect:/dashboard?username=" + username;
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }


    // Dashboard (safe on refresh/direct navigation)
    @GetMapping("/dashboard")
    public String dashboard(@RequestParam(value = "username", required = false) String username, Model model) {
        model.addAttribute("username", username != null ? username : "User");
        return "dashboard";
    }

    // Simple logout (no Spring Security)
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
