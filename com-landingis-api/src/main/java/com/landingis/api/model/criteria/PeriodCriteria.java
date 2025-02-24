package com.landingis.api.model.criteria;

import com.landingis.api.model.entity.Period;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PeriodCriteria {
    private String name;
    private LocalDate startDate;
    private LocalDate dueDate;
    private Integer state;

    public Specification<Period> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.trim().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }
            if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), startDate));
            }
            if (dueDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("dueDate"), dueDate));
            }
            if (state != null) {
                predicates.add(cb.equal(root.get("state"), state));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
