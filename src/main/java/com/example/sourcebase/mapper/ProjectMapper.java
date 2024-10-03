package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserDTO;
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

    // Chuyển đổi User thành UserDTO
    UserDTO toUserDTO(User user);

    // Chuyển đổi danh sách User thành danh sách UserDTO
    List<UserDTO> toUserDTOs(List<User> users);
}
