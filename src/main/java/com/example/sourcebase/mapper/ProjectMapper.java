package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserDTO;
import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
    @Mapping(source = "startDay", target = "startDay")
    @Mapping(source = "endDay", target = "endDay")
    Project toEntity(ProjectReqDTO dto);

    ProjectResDTO toResponseDTO(Project entity);
    List<ProjectResDTO> toProjectResDTOs(List<Project> projects);

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);


}
