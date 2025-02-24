package com.landingis.api.mapper;

import com.landingis.api.dto.period.PeriodDto;
import com.landingis.api.form.period.PeriodCreateForm;
import com.landingis.api.form.period.PeriodUpdateForm;
import com.landingis.api.model.entity.Period;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PeriodMapper {

    @Mappings({
            @Mapping(source = "periodName", target = "name"),
            @Mapping(source = "periodDescription", target = "description"),
            @Mapping(source = "periodStartDate", target = "startDate"),
            @Mapping(source = "periodDueDate", target = "dueDate"),
            @Mapping(source = "periodState", target = "state")
    })
    Period toEntity(PeriodCreateForm form);

    @Mappings({
            @Mapping(source = "periodName", target = "name"),
            @Mapping(source = "periodDescription", target = "description"),
            @Mapping(source = "periodStartDate", target = "startDate"),
            @Mapping(source = "periodDueDate", target = "dueDate"),
            @Mapping(source = "periodState", target = "state")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Period period, PeriodUpdateForm form);

    @Mappings({
            @Mapping(source = "name", target = "periodName"),
            @Mapping(source = "description", target = "periodDescription"),
            @Mapping(source = "startDate", target = "periodStartDate"),
            @Mapping(source = "dueDate", target = "periodDueDate"),
            @Mapping(source = "state", target = "periodState")
    })
    @Named("mapPeriodToDto")
    PeriodDto toDto(Period period);

    @IterableMapping(elementTargetType = PeriodDto.class, qualifiedByName = "mapPeriodToDto")
    List<PeriodDto> toDtoList(List<Period> periods);
}
