package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonFilter("SalaryFilter")
public class SalarySurveyDTO {
    private long id;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("employer")
    private String employer;

    @JsonProperty("location")
    private String location;

    @JsonProperty("years_at_employer")
    private String yearsAtEmployer;

    @JsonProperty("years_of_experience")
    private String yearsOfExperience;

    @JsonProperty("salary")
    private String salary;

    @JsonProperty("signing_bonus")
    private String signingBonus;

    @JsonProperty("annual_bonus")
    private String annualBonus;

    @JsonProperty("annual_stock_value")
    private String annualStockValue;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("additional_comments")
    private String additionalComments;

    @Override
    public String toString() {
        return "SalarySurveyDTO{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", timeStamp='" + timestamp + '\'' +
                ", employer='" + employer + '\'' +
                ", location='" + location + '\'' +
                ", yearsAtEmployer='" + yearsAtEmployer + '\'' +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                ", salary='" + salary + '\'' +
                ", signingBonus='" + signingBonus + '\'' +
                ", annualBonus='" + annualBonus + '\'' +
                ", annualStockValue='" + annualStockValue + '\'' +
                ", gender='" + gender + '\'' +
                ", additionalComment='" + additionalComments + '\'' +
                '}';
    }
}
