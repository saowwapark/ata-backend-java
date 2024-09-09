package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "salary_survey")
@Setter
@Getter
public class SalarySurvey {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "Timestamp")
    private String timestamp;

    @Column(name = "Employer")
    private String employer;

    @Column(name = "Location")
    private String location;

    @Column(name = "Job Title")
    private String job_title;

    @Column(name = "Years at Employer")
    private String years_at_employer;

    @Column(name = "Years of Experience")
    private String years_of_experience;

    @Column(name = "Salary")
    private String salary;

    @Column(name = "Signing Bonus")
    private String signing_bonus;

    @Column(name = "Annual Bonus")
    private String annual_bonus;

    @Column(name = "Annual Stock Value/Bonus")
    private String annual_stock_value;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Additional Comments", length = 500)
    private String additional_comments;

    @Override
    public String toString() {
        return "SalarySurvey{" +
                "id=" + id +
                ", time_stamp='" + timestamp + '\'' +
                ", employer='" + employer + '\'' +
                ", location='" + location + '\'' +
                ", job_title='" + job_title + '\'' +
                ", years_at_employer='" + years_at_employer + '\'' +
                ", years_of_experience='" + years_of_experience + '\'' +
                ", salary='" + salary + '\'' +
                ", signing_bonus='" + signing_bonus + '\'' +
                ", annual_bonus='" + annual_bonus + '\'' +
                ", annual_stock_value='" + annual_stock_value + '\'' +
                ", gender='" + gender + '\'' +
                ", additionalComments='" + additional_comments + '\'' +
                '}';
    }
}
