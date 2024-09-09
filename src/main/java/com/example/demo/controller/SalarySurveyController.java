package com.example.demo.controller;

import com.example.demo.dto.SalarySurveyDTO;
import com.example.demo.service.SalarySurveyService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SalarySurveyController {
    private final SalarySurveyService salarySurveyService;
    private static final Logger logger = Logger.getLogger(SalarySurveyController.class.getName());
    public SalarySurveyController(SalarySurveyService salarySurveyService) {
        this.salarySurveyService = salarySurveyService;
    }

    @GetMapping("/job_data")
    public MappingJacksonValue getJobData(
            @RequestParam(required = false) Map<String, String> params,
            @RequestParam(required = false, name = "fields") List<String> fields,
            @RequestParam(required = false, name = "sort") List<String> sorts,
            @RequestParam(required = false, name = "sort_type") List<String> sortTypes) {

        logger.log(Level.INFO, "params: " + params);

        // Handle encoded bracketed parameters manually
        Map<String, String> filters = getFilters(params);

        List<SalarySurveyDTO> dataList = salarySurveyService.findJobData(filters, fields, sorts, sortTypes);

        SimpleFilterProvider filterProvider = new SimpleFilterProvider();

        filterProvider.addFilter("SalaryFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept(fields.toArray(new String[0])));

        MappingJacksonValue mapping = new MappingJacksonValue(dataList);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    private static Map<String, String> getFilters(Map<String, String> params) {

        // Extract filters
        Map<String, String> filters = new HashMap<>();
        params.forEach((key, value) -> {
            if (!"fields".equals(key) && !"sort".equals(key) && !"sort_type".equals(key)) {
                filters.put(key, value);
            }
        });
        return filters;
    }
}
