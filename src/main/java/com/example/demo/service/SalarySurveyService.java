package com.example.demo.service;

import com.example.demo.dto.SalarySurveyDTO;
import com.example.demo.model.SalarySurvey;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalarySurveyService {

    @PersistenceContext
    private EntityManager entityManager;


    public List<SalarySurveyDTO> findJobData(Map<String, String> filters, List<String> fields, List<String> sortColumns, List<String> sortTypes) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<SalarySurvey> root = query.from(SalarySurvey.class);

        // Dynamic field selection
        List<Selection<?>> selections = new ArrayList<>();
        if (fields != null && !fields.isEmpty()) {
            for (String field : fields) {
                selections.add((root.get(field).alias(field)));
            }
        } else {
            selections.add(root);
        }
        query.multiselect(selections);

        // Dynamic filters
        List<Predicate> predicates = new ArrayList<>();
        filters.forEach((key, value) -> {
            if (key.contains("[gte]")) {
                String column = key.replace("[gte]", "");
                predicates.add(cb.greaterThanOrEqualTo(root.get(column), value));
            } else if (key.contains("[gt]")) {
                String column = key.replace("[gt]", "");
                predicates.add(cb.greaterThan(root.get(column), value));
            }
            else if (key.contains("[lte]")) {
                String column = key.replace("[lte]", "");
                predicates.add(cb.lessThanOrEqualTo(root.get(column), value));
            } else if (key.contains("[lt]")) {
                String column = key.replace("[lt]", "");
                predicates.add(cb.lessThan(root.get(column), value));
            }
            else {
                predicates.add(cb.like(root.get(key), '%' + value + '%'));
            }
        });
        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // Dynamic sorting
        if (sortColumns != null && !sortColumns.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < sortColumns.size(); i++) {
                String sortColumn = sortColumns.get(i);
                String sortType = sortTypes.get(i);
                if ("DESC".equalsIgnoreCase(sortType)) {
                    orders.add(cb.desc(root.get(sortColumn)));
                } else {
                    orders.add(cb.asc(root.get(sortColumn)));
                }
            }
            query.orderBy(orders);
        }


        List<Tuple> tupleList = entityManager.createQuery(query).getResultList();
        return tupleList.stream()
                .map(tuple -> mapTupleToDTO(tuple, fields))
                .collect(Collectors.toList());
    }

    private SalarySurveyDTO mapTupleToDTO(Tuple tuple, List<String> fields) {
        SalarySurveyDTO dto = new SalarySurveyDTO();

        if (fields.contains("timestamp")) {
            dto.setTimestamp(tuple.get("timestamp", String.class));
        }
        if (fields.contains("employer")) {
            dto.setEmployer(tuple.get("employer", String.class));
        }
        if (fields.contains("location")) {
            dto.setLocation(tuple.get("location", String.class));
        }
        if (fields.contains("job_title")) {
            dto.setJobTitle(tuple.get("job_title", String.class));
        }
        if (fields.contains("years_at_employer")) {
            dto.setYearsAtEmployer(tuple.get("years_at_employer", String.class));
        }
        if (fields.contains("years_of_experience")) {
            dto.setYearsOfExperience(tuple.get("years_of_experience", String.class));
        }
        if (fields.contains("salary")) {
            dto.setSalary(tuple.get("salary", String.class));
        }
        if (fields.contains("signing_bonus")) {
            dto.setSigningBonus(tuple.get("signing_bonus", String.class));
        }
        if (fields.contains("annual_bonus")) {
            dto.setAnnualBonus(tuple.get("annual_bonus", String.class));
        }
        if (fields.contains("annual_stock_value")) {
            dto.setAnnualStockValue(tuple.get("annual_stock_value", String.class));
        }
        if (fields.contains("gender")) {
            dto.setGender(tuple.get("gender", String.class));
        }
        if (fields.contains("additional_comments")) {
            dto.setAdditionalComments(tuple.get("additional_comments", String.class));
        }
        // Add more fields as necessary

        return dto;
    }

}