package com.landingis.api.mapper;

import com.landingis.api.dto.lecturerscheduler.LecturerSchedulerDto;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerCreateForm;
import com.landingis.api.form.lecturerscheduler.LecturerSchedulerUpdateForm;
import com.landingis.api.model.entity.LecturerScheduler;
import com.landingis.api.model.entity.Period;
import com.landingis.api.repository.PeriodRepository;
import com.landingis.api.util.MappingUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PeriodMapper.class})
public abstract class LecturerSchedulerMapper {

    @Autowired
    private PeriodRepository periodRepository;

    @Mappings({
            @Mapping(source = "lecturerId", target = "lecturerId"),
            @Mapping(source = "courseId", target = "courseId"),
            @Mapping(source = "periodId", target = "period", qualifiedByName = "mapPeriodIdToPeriod")
    })
    public abstract LecturerScheduler toEntity(LecturerSchedulerCreateForm form);

    @Mappings({
            @Mapping(source = "lecturerId", target = "lecturerId"),
            @Mapping(source = "courseId", target = "courseId"),
            @Mapping(source = "periodId", target = "period", qualifiedByName = "mapPeriodIdToPeriod")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntity(@MappingTarget LecturerScheduler lecturerScheduler, LecturerSchedulerUpdateForm form);

    @Mappings({
            @Mapping(source = "lecturerId", target = "lecturerId"),
            @Mapping(source = "courseId", target = "courseId"),
            @Mapping(source = "period", target = "period", qualifiedByName = "mapPeriodToDto")
    })
    @Named("mapLecturerSchedulerToDto")
    public abstract LecturerSchedulerDto toDto(LecturerScheduler lecturerScheduler);

    @IterableMapping(elementTargetType = LecturerSchedulerDto.class, qualifiedByName = "mapLecturerSchedulerToDto")
    public abstract List<LecturerSchedulerDto> toDtoList(List<LecturerScheduler> lecturerSchedulers);

    @Named("mapPeriodIdToPeriod")
    public Period mapPeriodIdToPeriod(Long periodId) {
        return MappingUtils.getEntityById(periodRepository, periodId, Period.class);
    }
}
