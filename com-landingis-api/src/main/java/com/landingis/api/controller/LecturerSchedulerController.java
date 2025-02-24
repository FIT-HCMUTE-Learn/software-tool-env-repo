package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.lecturerscheduler.LecturerSchedulerDto;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerCreateForm;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import com.landingis.api.mapper.LecturerSchedulerMapper;
import com.landingis.api.model.criteria.LecturerSchedulerCriteria;
import com.landingis.api.model.entity.LecturerScheduler;
import com.landingis.api.repository.LecturerSchedulerRepository;
import com.landingis.api.repository.PeriodRepository;
import com.landingis.api.util.ApiMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/lecturer-scheduler/")
public class LecturerSchedulerController {

    @Autowired
    private LecturerSchedulerRepository lecturerSchedulerRepository;

    @Autowired
    private LecturerSchedulerMapper lecturerSchedulerMapper;

    @Autowired
    private PeriodRepository periodRepository;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<LecturerSchedulerDto>>> getAll(
            LecturerSchedulerCriteria lecturerSchedulerCriteria,
            Pageable pageable
    ) {
        Specification<LecturerScheduler> specification = lecturerSchedulerCriteria.getSpecification();
        Page<LecturerScheduler> page = lecturerSchedulerRepository.findAll(specification, pageable);

        PaginationDto<LecturerSchedulerDto> result = new PaginationDto<>(
                lecturerSchedulerMapper.toDtoList(page.getContent()),
                page.getTotalElements(),
                page.getTotalPages()
        );


        ApiMessageDto<PaginationDto<LecturerSchedulerDto>> response = ApiMessageUtils
                .success(result, "Successfully retrieved all lecture-schedulers");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> getById(@PathVariable Long id) {
        ApiMessageDto<LecturerSchedulerDto> response = ApiMessageUtils
                .success(lecturerSchedulerMapper.toDto(findLecturerSchedulerById(id)), "Successfully retrieved lecturer-scheduler by id");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('C_POST')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> createLecturerScheduler(@Valid @RequestBody LecturerSchedulerCreateForm form) {
        if (periodRepository.findById(form.getPeriodId()).isEmpty()) {
            throw new ResourceNotFoundException("Period with id " + form.getPeriodId() + " not found");
        }
        if (lecturerSchedulerRepository.findByLecturerIdAndCourseIdAndPeriod(form.getLecturerId(), form.getCourseId(), form.getPeriodId())
                .isPresent()) {
            throw new BusinessException("Lecturer have registered course in period ("
                    + form.getLecturerId() + ", " + form.getCourseId() + ", " + form.getPeriodId() + ")");
        }

        LecturerScheduler lecturerScheduler = lecturerSchedulerMapper.toEntity(form);
        LecturerScheduler savedLecturerScheduler = lecturerSchedulerRepository.save(lecturerScheduler);

        ApiMessageDto<LecturerSchedulerDto> response = ApiMessageUtils
                .success(lecturerSchedulerMapper.toDto(savedLecturerScheduler), "Successfully created lecturer-scheduler");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('C_PUT')")
    public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> updateLecturerScheduler(
            @Valid @RequestBody LecturerSchedulerUpdateForm form
    ) {
        if (form.getPeriodId() != null) {
            periodRepository.findById(form.getPeriodId())
                    .orElseThrow(() -> new ResourceNotFoundException("Period with id " + form.getPeriodId() + " not found"));
        }
        LecturerScheduler lecturerScheduler = findLecturerSchedulerById(form.getLecturerSchedulerId());
        lecturerSchedulerMapper.updateEntity(lecturerScheduler, form);

        Optional<LecturerScheduler> test = lecturerSchedulerRepository.findByLecturerIdAndCourseIdAndPeriod(
                lecturerScheduler.getLecturerId(), lecturerScheduler.getCourseId(), lecturerScheduler.getPeriod().getId()
        );
        if (test.isPresent() && !test.get().getId().equals(lecturerScheduler.getId())) {
            throw new BusinessException("Lecturer have registered course in period ("
                    + form.getLecturerId() + ", " + form.getCourseId() + ", " + form.getPeriodId() + ")");
        }

        LecturerScheduler updatedLecturerScheduler = lecturerSchedulerRepository.save(lecturerScheduler);

        ApiMessageDto<LecturerSchedulerDto> response = ApiMessageUtils
                .success(lecturerSchedulerMapper.toDto(updatedLecturerScheduler), "Successfully updated lecturer-scheduler");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deleteLecturerScheduler(@PathVariable Long id) {
        LecturerScheduler lecturerScheduler = findLecturerSchedulerById(id);
        lecturerSchedulerRepository.delete(lecturerScheduler);

        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Successfully deleted period");

        return ResponseEntity.ok(response);
    }

    private LecturerScheduler findLecturerSchedulerById(Long id) {
        return lecturerSchedulerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Lecturer scheduler with id " + id + " not found")
        );
    }
}
