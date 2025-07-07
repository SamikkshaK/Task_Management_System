package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.Employer;
import com.careercrafter.jobportal.entity.JobSeeker;
import com.careercrafter.jobportal.entity.User;
import com.careercrafter.jobportal.entity.User.Role;
import com.careercrafter.jobportal.repo.EmployerRepo;
import com.careercrafter.jobportal.repo.JobSeekerRepo;
import com.careercrafter.jobportal.repo.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired private UserRepo userRepo;
    @Autowired private EmployerRepo employerRepo;
    @Autowired private JobSeekerRepo jobSeekerRepo;

    // Home page
    @GetMapping("/")
    public String index() {
        return "forward:/index.jsp";
    }

    // Register page
    @GetMapping("/register")
    public String showRegister() {
        return "forward:/register.jsp";
    }

    // Login page
    @GetMapping("/login")
    public String showLogin() {
        return "forward:/login.jsp";
    }

    // Handle Registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String fullName,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {

        // Normalize email
        email = email.trim().toLowerCase();

        // Check if email already exists
        if (userRepo.existsByEmail(email)) {
            model.addAttribute("message", "Email already exists: " + email);
            return "forward:/register.jsp";
        }

        // Try parsing role
        Role enumRole;
        try {
            enumRole = Role.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", "Invalid role selected.");
            return "forward:/register.jsp";
        }

        // Create user
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);  // set the normalized version
        user.setPassword(password);
        user.setRole(enumRole);
        

        // Create employer or seeker
        if (enumRole == Role.EMPLOYER) {
            Employer employer = new Employer();
            employer.setFullName(fullName);
            employer.setEmail(email);
            employer.setPassword(password);
            employer.setRole(Role.EMPLOYER);
            employer.setCompany(null); // Optional
            employerRepo.save(employer);
        } else {
            JobSeeker seeker = new JobSeeker();
            seeker.setFullName(fullName);
            seeker.setEmail(email);
            seeker.setPassword(password);
            seeker.setRole(Role.JOB_SEEKER);
            seeker.setEducation("Not Set");
            seeker.setExperience("0");
            seeker.setResumeLink("N/A");
            jobSeekerRepo.save(seeker);
        }

        model.addAttribute("message", "Registration successful. Please login.");
        return "forward:/login.jsp";
    }


    // Handle Login
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {

        User user = userRepo.findByEmailAndPassword(email, password);
        if (user == null) {
            model.addAttribute("message", "Invalid credentials.");
            return "forward:/login.jsp";
        }

        session.setAttribute("user", user);

        if (user.getRole() == Role.EMPLOYER) {
            return "redirect:/employer/home";
        } else if (user.getRole() == Role.JOB_SEEKER) {
            return "redirect:/user/home";
        } else {
            model.addAttribute("message", "Unknown role.");
            return "forward:/login.jsp";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // Utility to validate role
    private boolean checkRole(HttpSession session, Role expectedRole) {
        User user = (User) session.getAttribute("user");
        return user != null && user.getRole() == expectedRole;
    }
}
