package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssessMapper {
    AssessMapper INSTANCE = Mappers.getMapper(AssessMapper.class);

    AssessResDto toAssessResDto(Assess assess);

    Assess toAssess(AssessReqDTO assessReqDto);
}
