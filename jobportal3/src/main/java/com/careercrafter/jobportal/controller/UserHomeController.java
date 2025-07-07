package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.*;
import com.careercrafter.jobportal.repo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHomeController {

    @Autowired private JobRepo jobRepo;
    @Autowired private ApplicationRepo applicationRepo;
    @Autowired private JobSeekerRepo jobSeekerRepo;
    @Autowired private ResumeRepo resumeRepo;

    @GetMapping("/home")
    public String userHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.JOB_SEEKER) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "forward:/userhome.jsp";
    }

    @GetMapping("/jobs")
    public String viewAvailableJobs(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.JOB_SEEKER) {
            return "redirect:/login";
        }
        model.addAttribute("jobs", jobRepo.findAll());
        return "forward:/jobs.jsp";
    }

    @PostMapping("/apply")
    public String applyToJob(@RequestParam("jobId") Long jobId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.JOB_SEEKER) {
            return "redirect:/login";
        }

        Job job = jobRepo.findById(jobId).orElse(null);
        if (job == null) {
            model.addAttribute("message", "Job not found.");
        } else if (applicationRepo.existsByJobIdAndSeekerEmail(jobId, user.getEmail())) {
            model.addAttribute("message", "You have already applied to this job.");
        } else {
            Application application = new Application();
            application.setJob(job);
            application.setSeekerEmail(user.getEmail());
            application.setAppliedDate(LocalDate.now());
            applicationRepo.save(application);
            model.addAttribute("message", "Application submitted successfully.");
        }

        model.addAttribute("jobs", jobRepo.findAll());
        return "forward:/jobs.jsp";
    }

    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.JOB_SEEKER) {
            return "redirect:/login";
        }

        JobSeeker seeker = jobSeekerRepo.findByEmail(user.getEmail());
        model.addAttribute("seeker", seeker);
        return "forward:/profile.jsp";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("seeker") JobSeeker formSeeker, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.JOB_SEEKER) {
            return "redirect:/login";
        }

        JobSeeker seeker = jobSeekerRepo.findByEmail(user.getEmail());
        if (seeker != null) {
            seeker.setFullName(formSeeker.getFullName());
            seeker.setEducation(formSeeker.getEducation());
            seeker.setExperience(formSeeker.getExperience());
            seeker.setResumeLink(formSeeker.getResumeLink());
            jobSeekerRepo.save(seeker);
            model.addAttribute("message", "Profile updated successfully.");
        }

        model.addAttribute("seeker", seeker);
        return "forward:/profile.jsp";
    }

    @GetMapping("/my_applications")
    public String viewMyApplications(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.JOB_SEEKER) {
            return "redirect:/login";
        }

        List<Application> apps = applicationRepo.findBySeekerEmail(user.getEmail());
        model.addAttribute("applications", apps);
        return "forward:/my_applications.jsp";
    }

    @GetMapping("/upload_resume")
    public String showResumeUploadPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return (user == null || user.getRole() != User.Role.JOB_SEEKER) ? "redirect:/login" : "forward:/upload_resume.jsp";
    }

    @PostMapping("/upload-resume")
    public String uploadResume(@RequestParam("file") MultipartFile file,
                               @RequestParam("description") String description,
                               HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.JOB_SEEKER) {
            return "redirect:/login";
        }

        if (file.isEmpty()) {
            model.addAttribute("message", "Please choose a file to upload.");
            return "forward:/upload_resume.jsp";
        }

        try {
            String uploadDir = session.getServletContext().getRealPath("/uploads/resumes");
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String safeFileName = "resume_" + user.getId() + "_" + originalFilename;
            Path targetPath = uploadPath.resolve(safeFileName);
            file.transferTo(targetPath.toFile());

            Resume resume = new Resume();
            resume.setFileUrl("/uploads/resumes/" + safeFileName);
            resume.setFileType(file.getContentType());
            resume.setDescription(description);

            JobSeeker jobSeeker = jobSeekerRepo.findById(user.getId()).orElse(null);
            if (jobSeeker != null) {
                resume.setJobSeeker(jobSeeker);
                resumeRepo.save(resume);
                model.addAttribute("message", "Resume uploaded successfully.");
            } else {
                model.addAttribute("message", "Job seeker not found.");
            }

        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload resume: " + e.getMessage());
        }

        return "forward:/upload_resume.jsp";
    }


    @PostMapping("/delete-my_applications")
    public String deleteApplication(@RequestParam("applicationId") Long applicationId,
                                    RedirectAttributes redirectAttributes) {
        applicationRepo.deleteById(applicationId);
        redirectAttributes.addFlashAttribute("message", "Application deleted successfully!");
        return "redirect:/user/my_applications";
    }
}
