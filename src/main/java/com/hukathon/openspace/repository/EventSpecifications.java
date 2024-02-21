package com.hukathon.openspace.repository;

import com.hukathon.openspace.dto.SearchRequest;
import com.hukathon.openspace.entity.Event;
import com.hukathon.openspace.entity.EventTag;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventSpecifications {

    public static Specification<Event> searchByCriteria(SearchRequest searchRequest) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            Join<Event, EventTag> tagJoin = root.join("tags", JoinType.INNER);
            if (searchRequest.getTags() != null) {
                List<Predicate> tagPredicates = new ArrayList<>();
                for (String tag : searchRequest.getTags()) {
                    tagPredicates.add(criteriaBuilder.equal(tagJoin.get("nameTag"), tag));
                }
                predicate = criteriaBuilder.and(tagPredicates.toArray(new Predicate[0]));
            }

            if (searchRequest.getText() != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + searchRequest.getText() + "%"));
            }

            if (searchRequest.getFromDateDto() != null) {
                String monthString = searchRequest.getFromDateDto().getFromMonth();
                String yearString = searchRequest.getFromDateDto().getFromYear();
                String startDateString = yearString + "-" + monthString;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                try {
                    Date startDate = dateFormat.parse(startDateString);
                    System.out.println("Chuỗi " + startDateString + " đã được chuyển thành: " + startDate);
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("time"), startDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (searchRequest.getToDateDto() != null) {
                String monthString = searchRequest.getToDateDto().getToMonth();
                String yearString = searchRequest.getToDateDto().getToYear();
                String endDateString = yearString + "-" + monthString;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
                try {
                    Date endDate = dateFormat.parse(endDateString);
                    System.out.println("Chuỗi " + endDateString + " đã được chuyển thành: " + endDate);

                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("time"), endDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            return predicate;
        };
    }

}

