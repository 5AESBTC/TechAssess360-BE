package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import com.example.sourcebase.mapper.ProjectMapper;
import com.example.sourcebase.repository.IProjectRepository;
import com.example.sourcebase.service.IProjectService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProjectService implements IProjectService {

    IProjectRepository projectRepository;

    public ProjectResDTO addProject(ProjectReqDTO projectRequest) {
        Project project = ProjectMapper.INSTANCE.toEntity(projectRequest);

        Project savedProject = projectRepository.save(project);

        return ProjectMapper.INSTANCE.toResponseDTO(savedProject);
    }
}
