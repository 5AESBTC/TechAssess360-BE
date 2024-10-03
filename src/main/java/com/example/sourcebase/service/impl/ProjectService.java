package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.exception.AppException;
import com.example.sourcebase.mapper.ProjectMapper;
import com.example.sourcebase.repository.IProjectRepository;
import com.example.sourcebase.repository.IUserProjectRepository;
import com.example.sourcebase.service.IProjectService;
import com.example.sourcebase.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService implements IProjectService {

    IProjectRepository projectRepository;
    IUserProjectRepository userProjectRepository;

    public ProjectResDTO addProject(ProjectReqDTO projectRequest) {
        Project project = ProjectMapper.INSTANCE.toEntity(projectRequest);

        Project savedProject = projectRepository.save(project);

        return ProjectMapper.INSTANCE.toResponseDTO(savedProject);
    }

    @Override
    public List<ProjectResDTO> getAll() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectResDTO> projectResDTOS = ProjectMapper.INSTANCE.toProjectResDTOs(projects);

        for (ProjectResDTO projectResDTO : projectResDTOS) {
            List<User> users = userProjectRepository.findUsersByProjectId(projectResDTO.getId());
            projectResDTO.setMembers(ProjectMapper.INSTANCE.toUserDTOs(users));
        }

        return projectResDTOS;
    }
}
