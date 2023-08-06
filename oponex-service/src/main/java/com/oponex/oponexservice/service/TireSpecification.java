package com.oponex.oponexservice.service;

import com.oponex.oponexservice.model.TireSearchDTO;
import com.oponex.oponexservice.model.dao.Tire;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TireSpecification implements Specification<Tire> {
    private final TireSearchDTO tireSearchDTO;

    @Override
    public Predicate toPredicate(Root<Tire> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (tireSearchDTO.brand() != null) {
            predicates.add(criteriaBuilder.equal(root.get("brand"), tireSearchDTO.brand().name()));
        }

        if (tireSearchDTO.season() != null) {
            predicates.add(criteriaBuilder.equal(root.get("season"), tireSearchDTO.season().name()));
        }

        if (tireSearchDTO.width() != null) {
            predicates.add(criteriaBuilder.equal(root.get("width"), tireSearchDTO.width()));
        }

        if (tireSearchDTO.profile() != null) {
            predicates.add(criteriaBuilder.equal(root.get("profile"), tireSearchDTO.profile()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
