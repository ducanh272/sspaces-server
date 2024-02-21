package com.hukathon.openspace.repository;

import com.hukathon.openspace.dto.SearchRequest;
import com.hukathon.openspace.entity.Event;
import com.hukathon.openspace.entity.Place;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class PlaceSpecifications {
    public static Specification<Place> searchByCriteria(SearchRequest searchRequest) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (searchRequest.getText() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + searchRequest.getText() + "%"));
            }

            if (searchRequest.getDistrict() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("address"), "%" + searchRequest.getDistrict() + "%"));
            }
            return predicate;
        };
    }
}
