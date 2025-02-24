package com.landingis.api.model.criteria;

import com.landingis.api.model.entity.LecturerScheduler;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LecturerSchedulerCriteria {
    private Long lecturerId;
    private Long courseId;
    private Long periodId;

    public Specification<LecturerScheduler> getSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Object, Object> periodJoin = root.join("period");

            if (lecturerId != null) {
                predicates.add(cb.equal(root.get("lecturerId"), lecturerId));
            }
            if (courseId != null) {
                predicates.add(cb.equal(root.get("courseId"), courseId));
            }
            if (periodId != null) {
                predicates.add(cb.equal(periodJoin.get("id"), periodId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
