package com.flowable.flowableboot.mappers;

import com.flowable.flowableboot.dto.BaseDto;
import com.flowable.flowableboot.dto.UmsTaskGetDto;
import com.flowable.flowableboot.dto.UmsTaskPostDto;
import com.flowable.flowableboot.model.BaseEntity;
import com.flowable.flowableboot.model.UmsTask;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        uses = {CustomEnumTransformation.class},
        componentModel= "spring"
)
public interface MapStructMapper {

    UmsTaskGetDto umsTaskToUmsTaskGetDto(UmsTask umsTask);
    UmsTask umsTaskPostDtoToUmsTask(UmsTaskPostDto umsTaskPostDto);

    BaseDto baseEntityToBaseDto(BaseEntity baseEntity);
    BaseEntity baseDtoToBaseEntity(BaseDto baseDto);

    List<UmsTaskGetDto> umsTaskListToUmsTaskGetDto(Iterable<UmsTask> umsTasks);
}
