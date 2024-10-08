package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface IProjectService {
    ProjectResDTO addProject(ProjectReqDTO projectRequest);

    List<ProjectResDTO> getAll();

    Object getPrjectById(Long id);

    boolean deleteProject(Long id);

    ProjectResDTO updateProject(Long id, ProjectReqDTO projectReqDTO);
}
