package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
    @Mapping(source = "startDay", target = "startDay")
    @Mapping(source = "endDay", target = "endDay")
    Project toEntity(ProjectReqDTO dto);
    ProjectResDTO toResponseDTO(Project entity);
}
