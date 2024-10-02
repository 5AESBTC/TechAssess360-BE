package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import org.springframework.stereotype.Service;

@Service
public interface IProjectService {
    ProjectResDTO addProject(ProjectReqDTO projectRequest);
}
