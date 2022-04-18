package com.flowable.flowableboot.mappers;

import com.flowable.flowableboot.dtos.BaseDto;
import com.flowable.flowableboot.dtos.UmsTaskGetDto;
import com.flowable.flowableboot.dtos.UmsTaskPostDto;
import com.flowable.flowableboot.model.BaseEntity;
import com.flowable.flowableboot.model.UmsTask;
import org.mapstruct.Mapper;

@Mapper(
        uses = {CustomEnumTransformation.class},
        componentModel= "spring"
)
public interface MapStructMapper {

    UmsTaskGetDto umsTaskToUmsTaskGetDto(UmsTask umsTask);
    UmsTask umsTaskPostDtoToUmsTask(UmsTaskPostDto umsTaskPostDto);

    BaseDto baseEntityToBaseDto(BaseEntity baseEntity);
    BaseEntity baseDtoToBaseEntity(BaseDto baseDto);

}
