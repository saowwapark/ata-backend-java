package com.example.demo.repository;
import com.example.demo.model.SalarySurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalarySurveyRepository extends JpaRepository<SalarySurvey, Long> {

    // 1. Select all SalarySurvey records
    List<SalarySurvey> findAll();

}
