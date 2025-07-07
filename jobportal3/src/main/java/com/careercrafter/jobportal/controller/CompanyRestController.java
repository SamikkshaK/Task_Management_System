package com.careercrafter.jobportal.controller;

import com.careercrafter.jobportal.entity.Company;
import com.careercrafter.jobportal.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyRestController {

    @Autowired private CompanyRepo companyRepo;

    @PostMapping
    public Company create(@RequestBody Company company) {
        return companyRepo.save(company);
    }

    @GetMapping
    public List<Company> getAll() {
        return companyRepo.findAll();
    }

    @GetMapping("/{id}")
    public Company getById(@PathVariable Long id) {
        return companyRepo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable Long id, @RequestBody Company updated) {
        Company c = companyRepo.findById(id).orElseThrow();
        c.setCompanyName(updated.getCompanyName());
        c.setLocation(updated.getLocation());
        c.setIndustry(updated.getIndustry());
        return companyRepo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        companyRepo.deleteById(id);
    }
}
