package com.careercrafter.jobportal.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "job_seekers")
public class JobSeeker extends User {

    private String resumeLink;
    private String education;
    private String experience;
	public String getResumeLink() {
		return resumeLink;
	}
	public void setResumeLink(String resumeLink) {
		this.resumeLink = resumeLink;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}

  
    
   }
