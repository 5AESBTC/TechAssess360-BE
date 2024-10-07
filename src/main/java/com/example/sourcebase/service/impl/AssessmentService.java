package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.AssessDetail;
import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.UserProjectAssessmentRequestDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectAssessmentResDTO;
import com.example.sourcebase.domain.dto.resdto.UserProjectAssessmentResponseDTO;
import com.example.sourcebase.exception.AppException;
import com.example.sourcebase.mapper.AssessmentMapper;
import com.example.sourcebase.repository.IAssessDetailRepository;
import com.example.sourcebase.repository.IAssessRepository;
import com.example.sourcebase.repository.IProjectRepository;
import com.example.sourcebase.repository.IUserRepository;
import com.example.sourcebase.service.IAssessmentService;
import com.example.sourcebase.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AssessmentService implements IAssessmentService {

     IAssessRepository assessRepository;

     IAssessDetailRepository assessDetailRepository;


     IUserRepository userRepository;

     IProjectRepository projectRepository;


     AssessmentMapper assessmentMapper;

    public UserProjectAssessmentResponseDTO getUserProjectAssessments(UserProjectAssessmentRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() ->  new AppException(ErrorCode.USER_NOT_FOUND));

        Project project = projectRepository.findById(requestDTO.getProjectId())
                .orElseThrow(() ->   new AppException(ErrorCode.PPOJECT_IS_EXIST));

        List<Assess> assessments = assessRepository.findByToUserAndProject(user, project);

        List<ProjectAssessmentResDTO> assessmentDTOs = assessments.stream()
                .map(this::convertToProjectAssessmentDTO)
                .collect(Collectors.toList());

        UserProjectAssessmentResponseDTO responseDTO = new UserProjectAssessmentResponseDTO();
        responseDTO.setUserId(user.getId());
        responseDTO.setUserName(user.getName());
        responseDTO.setProjectId(project.getId());
        responseDTO.setProjectName(project.getName());
        responseDTO.setAssessments(assessmentDTOs);

        return responseDTO;
    }

    private ProjectAssessmentResDTO convertToProjectAssessmentDTO(Assess assess) {
        ProjectAssessmentResDTO dto = assessmentMapper.assessToProjectAssessmentDTO(assess);
        List<AssessDetail> details = assessDetailRepository.findByAssess(assess);
        dto.setDetails(details.stream()
                .map(assessmentMapper::assessDetailToAssessmentDetailDTO)
                .collect(Collectors.toList()));
        return dto;
    }

}
