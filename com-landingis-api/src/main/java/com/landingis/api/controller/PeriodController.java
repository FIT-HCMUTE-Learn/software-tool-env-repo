package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.PaginationDto;
import com.landingis.api.dto.period.PeriodDto;
import com.landingis.api.exception.BusinessException;
import com.landingis.api.exception.ResourceNotFoundException;
import com.landingis.api.form.period.PeriodCreateForm;
import com.landingis.api.form.period.PeriodUpdateForm;
import com.landingis.api.mapper.PeriodMapper;
import com.landingis.api.model.criteria.PeriodCriteria;
import com.landingis.api.model.entity.Period;
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

@RestController
@RequestMapping("/api/period/")
public class PeriodController {

    @Autowired
    private PeriodRepository periodRepository;

    @Autowired
    private PeriodMapper periodMapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PaginationDto<PeriodDto>>> getAll(
            PeriodCriteria periodCriteria,
            Pageable pageable
    ) {
        Specification<Period> specification = periodCriteria.getSpecification();
        Page<Period> page = periodRepository.findAll(specification, pageable);

        PaginationDto<PeriodDto> result = new PaginationDto<>(
                periodMapper.toDtoList(page.getContent()),
                page.getTotalElements(),
                page.getTotalPages()
        );

        ApiMessageDto<PaginationDto<PeriodDto>> response = ApiMessageUtils
                .success(result, "Successfully retrieved all periods");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('C_GET')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> getById(@PathVariable Long id) {
        ApiMessageDto<PeriodDto> response = ApiMessageUtils
                .success(periodMapper.toDto(findPeriodById(id)), "Successfully retrieved period by id");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('C_POST')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> createPeriod(@Valid @RequestBody PeriodCreateForm form) {
        Period period = periodMapper.toEntity(form);

        checkStartDateAndDueDate(period);
        Period savedPeriod = periodRepository.save(period);

        ApiMessageDto<PeriodDto> response = ApiMessageUtils
                .success(periodMapper.toDto(savedPeriod), "Successfully created period");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('C_PUT')")
    public ResponseEntity<ApiMessageDto<PeriodDto>> updatePeriod(@Valid @RequestBody PeriodUpdateForm form) {
        Period period = findPeriodById(form.getPeriodId());
        periodMapper.updateEntity(period, form);

        checkStartDateAndDueDate(period);
        Period updatedPeriod = periodRepository.save(period);

        ApiMessageDto<PeriodDto> response = ApiMessageUtils
                .success(periodMapper.toDto(updatedPeriod), "Successfully updated period");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('C_DELETE')")
    public ResponseEntity<ApiMessageDto<Void>> deletePeriod(@PathVariable Long id) {
        Period period = findPeriodById(id);
        periodRepository.delete(period);

        ApiMessageDto<Void> response = ApiMessageUtils
                .success(null, "Successfully deleted period");

        return ResponseEntity.ok(response);
    }

    private Period findPeriodById(Long id){
        return periodRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Period with id " + id + " not found")
        );
    }

    private void checkStartDateAndDueDate(Period period) {
        if (period.getStartDate().isAfter(period.getDueDate())) {
            throw new BusinessException("Start date must be before due date");
        }
    }
}
