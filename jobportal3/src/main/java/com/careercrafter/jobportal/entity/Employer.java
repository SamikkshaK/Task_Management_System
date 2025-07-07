package com.careercrafter.jobportal.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "employers")
public class Employer extends User {

    private String designation;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Column(name = "employer_email")
    private String employerEmail;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getEmployerEmail() {
		return employerEmail;
	}

	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}
    
    

   
}
