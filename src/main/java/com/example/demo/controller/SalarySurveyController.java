package com.example.demo.controller;

import com.example.demo.dto.SalarySurveyDTO;
import com.example.demo.model.SalarySurvey;
import com.example.demo.service.SalarySurveyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SalarySurveyController {
    private final SalarySurveyService salarySurveyService;
    public SalarySurveyController(SalarySurveyService salarySurveyService) {
        this.salarySurveyService = salarySurveyService;
    }

    @GetMapping("/job_data")
    public List<?> getJobData(
            @RequestParam(required = false) Map<String, String> params,
            @RequestParam(required = false, name = "fields") List<String> fields,
            @RequestParam(required = false, name = "sort") List<String> sorts,
            @RequestParam(required = false, name = "sort_type") List<String> sortTypes) {
        System.out.println("allParams: " + params);
        System.out.println("fields: " + fields);
        System.out.println("sort: " + sorts);
        System.out.println("sort_type: " + sortTypes);
        // Extract filters
        Map<String, String> filters = new HashMap<>();
        params.forEach((key, value) -> {
            if (!"fields".equals(key) && !"sort".equals(key) && !"sort_type".equals(key)) {
                filters.put(key, value);
            }
        });

        List<SalarySurveyDTO> dataList = salarySurveyService.findJobData(filters, fields, sorts, sortTypes);

        return dataList;
    }
}
