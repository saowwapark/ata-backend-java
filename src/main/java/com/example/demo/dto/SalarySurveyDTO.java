package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SalarySurveyDTO {
    private long id;

    @JsonProperty("jobTitle")
    private String jobTitle;

    @JsonProperty("timeStamp")
    private String timeStamp;

    @JsonProperty("employer")
    private String employer;

    @JsonProperty("location")
    private String location;

    @JsonProperty("yearsAtEmployer")
    private String yearsAtEmployer;

    @JsonProperty("yearsOfExperience")
    private String yearsOfExperience;

    @JsonProperty("salary")
    private String salary;

    @JsonProperty("signingBonus")
    private String signingBonus;

    @JsonProperty("annualBonus")
    private String annualBonus;

    @JsonProperty("annualStockValue")
    private String annualStockValue;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("additionalComment")
    private String additionalComment;

    @Override
    public String toString() {
        return "SalarySurveyDTO{" +
                "id=" + id +
                ", jobTitle='" + jobTitle + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", employer='" + employer + '\'' +
                ", location='" + location + '\'' +
                ", yearsAtEmployer='" + yearsAtEmployer + '\'' +
                ", yearsOfExperience='" + yearsOfExperience + '\'' +
                ", salary='" + salary + '\'' +
                ", signingBonus='" + signingBonus + '\'' +
                ", annualBonus='" + annualBonus + '\'' +
                ", annualStockValue='" + annualStockValue + '\'' +
                ", gender='" + gender + '\'' +
                ", additionalComment='" + additionalComment + '\'' +
                '}';
    }
}
