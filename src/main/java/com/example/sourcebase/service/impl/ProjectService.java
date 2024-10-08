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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService implements IProjectService {

    IProjectRepository projectRepository;
    IUserProjectRepository userProjectRepository;


    @Override
    public ProjectResDTO addProject(ProjectReqDTO projectRequest) {
        if (projectRepository.existsByName(projectRequest.getName())) {
            throw new AppException(ErrorCode.PPOJECT_IS_EXIST);
        }
        validateProject(projectRequest);
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

    @Override
    public Object getPrjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(() -> new AppException(ErrorCode.PROJECT_NOT_FOUND));
    }

    @Override
    public boolean deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ProjectResDTO updateProject(Long id, ProjectReqDTO projectReqDTO) {

        validateProject(projectReqDTO);
        return projectRepository.findById(id).map(existingProject -> {

            existingProject.setName(projectReqDTO.getName());
            existingProject.setStartDay(projectReqDTO.getStartDay());
            existingProject.setEndDay(projectReqDTO.getEndDay());
            Project updatedProject = projectRepository.save(existingProject);
            return ProjectMapper.INSTANCE.toResponseDTO(updatedProject);
        }).orElse(null);
    }



    private void validateProject(ProjectReqDTO projectReqDTO) {

        LocalDate currentDate = LocalDate.now();

        if (!projectReqDTO.getStartDay().isAfter(currentDate)) {
            throw new AppException(ErrorCode.INVALID_START_DATE);
        }
        if (!projectReqDTO.getEndDay().isAfter(currentDate)) {
            throw new AppException(ErrorCode.INVALID_END_DATE);
        }
        if (!projectReqDTO.getEndDay().isAfter(projectReqDTO.getStartDay())) {
            throw new AppException(ErrorCode.END_DATE_BEFORE_START_DATE);
        }
    }
}
