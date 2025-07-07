package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.*;
import com.careercrafter.jobportal.repo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employer")
public class EmployerHomeController {

    @Autowired private JobRepo jobRepo;
    @Autowired private EmployerRepo employerRepo;
    @Autowired private ApplicationRepo applicationRepo;
    @Autowired private CompanyRepo companyRepo;
    @Autowired private JobSeekerRepo jobSeekerRepo;
    @Autowired private ResumeRepo resumeRepo;

    @GetMapping("/home")
    public String employerHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "forward:/employerhome.jsp";
    }

    @GetMapping("/post-job")
    public String showPostJobForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }
        model.addAttribute("job", new Job());
        return "forward:/post_job.jsp";
    }

    @PostMapping("/post-job")
    public String handlePostJob(@ModelAttribute Job job, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }

        Employer employer = employerRepo.findByEmail(user.getEmail());
        if (employer == null) {
            model.addAttribute("message", "Employer not found.");
            return "forward:/error.jsp";
        }

        job.setEmployerEmail(user.getEmail());
        if (employer.getCompany() != null) {
            job.setCompany(employer.getCompany());
        }

        jobRepo.save(job);
        model.addAttribute("message", "Job posted successfully.");
        return "forward:/success.jsp";
    }

    @GetMapping("/manage-jobs")
    public String manageJobs(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }

        List<Job> jobs = jobRepo.findByEmployerEmail(user.getEmail());
        model.addAttribute("jobs", jobs);
        return "forward:/manage_jobs.jsp";
    }

    @GetMapping("/applications")
    public String viewApplications(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }

        List<Application> applications = applicationRepo.findByJob_EmployerEmail(user.getEmail());
        Map<String, String> resumeMap = new HashMap<>();

        for (Application app : applications) {
            JobSeeker seeker = jobSeekerRepo.findByEmail(app.getSeekerEmail());
            if (seeker != null) {
                Resume resume = resumeRepo.findTopByJobSeekerOrderByIdDesc(seeker);
                if (resume != null) {
                    resumeMap.put(app.getSeekerEmail(), resume.getFileUrl());
                }
            }
        }

        model.addAttribute("applications", applications);
        model.addAttribute("resumeMap", resumeMap);
        return "forward:/applications.jsp";
    }

    @GetMapping("/update-company")
    public String showUpdateCompanyForm(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }

        Employer employer = employerRepo.findByEmail(user.getEmail());
        if (employer.getCompany() == null) {
            employer.setCompany(new Company());
        }

        model.addAttribute("employer", employer);
        return "forward:/update_company.jsp";
    }

    @PostMapping("/update-company")
    public String updateCompany(@ModelAttribute Employer employerForm,
                                HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }

        Employer employer = employerRepo.findByEmail(user.getEmail());
        if (employer != null) {
            Company formCompany = employerForm.getCompany();
            if (formCompany != null) {
                if (formCompany.getCompanyId() == null) {
                    formCompany = companyRepo.save(formCompany);
                } else {
                    Company existingCompany = companyRepo.findById(formCompany.getCompanyId()).orElse(null);
                    if (existingCompany != null) {
                        existingCompany.setCompanyName(formCompany.getCompanyName());
                        existingCompany.setIndustry(formCompany.getIndustry());
                        existingCompany.setLocation(formCompany.getLocation());
                        formCompany = companyRepo.save(existingCompany);
                    }
                }
                employer.setCompany(formCompany);
            }

            employer.setPhone(employerForm.getPhone());
            employer.setDesignation(employerForm.getDesignation());
            employerRepo.save(employer);
            model.addAttribute("message", "Company profile updated.");
        }

        return "forward:/success.jsp";
    }

    @GetMapping("/jobs")
    public String listPostedJobs(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }

        model.addAttribute("jobs", jobRepo.findByEmployerEmail(user.getEmail()));
        return "forward:/manage_jobs.jsp";
    }

    @GetMapping("/edit-job")
    public String showEditJobPage(@RequestParam("jobId") Long jobId, Model model, HttpSession session) {
        Job job = jobRepo.findById(jobId).orElse(null);
        if (job == null) {
            model.addAttribute("message", "Job not found.");
            return "redirect:/employer/jobs";
        }

        model.addAttribute("job", job);
        return "forward:/edit_job.jsp";
    }

    @PostMapping("/update-job")
    public String updateJob(@ModelAttribute Job job, RedirectAttributes redirectAttributes) {
        Job existingJob = jobRepo.findById(job.getId()).orElse(null);
        if (existingJob != null) {
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setLocation(job.getLocation());
            existingJob.setSalary(job.getSalary());

            if (existingJob.getCompany() != null && job.getCompany() != null) {
                existingJob.getCompany().setCompanyName(job.getCompany().getCompanyName());
            }

            jobRepo.save(existingJob);
            redirectAttributes.addFlashAttribute("message", "Job updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Job not found.");
        }

        return "redirect:/employer/jobs";
    }

    @PostMapping("/delete-job")
    public String deleteJob(@RequestParam("jobId") Long jobId,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.EMPLOYER) {
            return "redirect:/login";
        }

        List<Application> applications = applicationRepo.findByJob_Id(jobId);
        if (applications != null && !applications.isEmpty()) {
            applicationRepo.deleteAll(applications);
        }

        jobRepo.deleteById(jobId);
        redirectAttributes.addFlashAttribute("message", "Job deleted successfully.");
        return "redirect:/employer/jobs";
    }
}
