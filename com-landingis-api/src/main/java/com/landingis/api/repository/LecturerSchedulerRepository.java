package com.landingis.api.repository;

import com.landingis.api.model.entity.LecturerScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LecturerSchedulerRepository extends JpaRepository<LecturerScheduler, Long>, JpaSpecificationExecutor<LecturerScheduler> {

    @Query("SELECT ls FROM LecturerScheduler ls " +
            "WHERE ls.lecturerId = :lecturerId " +
            "AND ls.courseId = :courseId " +
            "AND ls.period.id = :periodId"
    )
    Optional<LecturerScheduler> findByLecturerIdAndCourseIdAndPeriod(
            @Param("lecturerId") Long lecturerId,
            @Param("courseId") Long courseId,
            @Param("periodId") Long periodId
    );
}
