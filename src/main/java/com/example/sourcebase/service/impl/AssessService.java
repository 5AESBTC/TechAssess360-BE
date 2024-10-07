package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.AssessDetail;
import com.example.sourcebase.domain.Project;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.UserProjectAssessmentRequestDTO;

import com.example.sourcebase.domain.dto.resdto.UserProjectAssessmentResponseDTO;
import com.example.sourcebase.exception.AppException;
import com.example.sourcebase.mapper.AssessDetailMapper;
import com.example.sourcebase.repository.*;
import com.example.sourcebase.mapper.AssessMapper;
import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDTO;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
import com.example.sourcebase.service.IAssessService;
import com.example.sourcebase.util.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AssessService implements IAssessService {
    AssessMapper assessMapper = AssessMapper.INSTANCE;
    AssessDetailMapper assessDetailMapper = AssessDetailMapper.INSTANCE;
    IAssessRepository assessRepository;
    IUserRepository userRepository;
    IAssessDetailRepository assessDetailRepository;
    ICriteriaRepository criteriaRepository;
    IQuestionRepository questionRepository;
    IProjectRepository projectRepository;

    @Override
    public AssessResDTO updateAssess(AssessReqDTO assessReqDto) {
        ETypeAssess type = null;
        if (assessReqDto.getUserId().equals(assessReqDto.getToUserId())) {
            type = ETypeAssess.SELF;
        } else if(userRepository.findById(Long.valueOf(assessReqDto.getUserId())).get().getUserRoles().equals("MANAGER")) {
            type = ETypeAssess.MANAGER;
        } else {
            type = ETypeAssess.TEAM;
        }
        User userReview = userRepository.findById(Long.valueOf(assessReqDto.getUserId())).get();
        User toUser = userRepository.findById(Long.valueOf(assessReqDto.getToUserId())).get();
        Assess assess = assessMapper.toAssess(assessReqDto);
        assess.setUser(userReview);
        assess.setToUser(toUser);
        assess.setAssessmentType(type);
        assess.setAssessmentDate(LocalDate.now());
        assessRepository.save(assess);
        assessReqDto.getAssessDetails().forEach(item -> {
            AssessDetail assessDetail = assessDetailMapper.toAssessDetail(item);
            assessDetail.setAssess(assess);
            if (item.getCriteriaId().equals("6")|| item.getCriteriaId().equals("7") || item.getCriteriaId().equals("8")) {
                assessDetail.setComment(true);
            }
            if (item.getQuestionId() != null) {
                assessDetail.setQuestion(questionRepository.findById(Long.valueOf(item.getQuestionId())).get());
            }else {
                assessDetail.setQuestion(null);
            }
            assessDetail.setCriteria(criteriaRepository.findById(Long.valueOf(item.getCriteriaId())).get());
            assessDetailRepository.save(assessDetail);
        });
        return assessMapper.toAssessResDto(assess);
    }

    @Override
    public List<AssessResDTO> getAssessByUserId(String userId) {
        return List.of();
    }
//    public UserProjectAssessmentResponseDTO getUserProjectAssessments(UserProjectAssessmentRequestDTO requestDTO) {
//        User user = userRepository.findById(requestDTO.getUserId())
//                .orElseThrow(() ->  new AppException(ErrorCode.USER_NOT_FOUND));
//
//        Project project = projectRepository.findById(requestDTO.getProjectId())
//                .orElseThrow(() ->   new AppException(ErrorCode.PPOJECT_IS_EXIST));
//
//        List<Assess> assessments = assessRepository.findByToUserAndProject(user, project);
//
//        List<AssessResDTO> assessmentDTOs = assessments.stream()
//                .map(this::convertToProjectAssessmentDTO)
//                .collect(Collectors.toList());
//
//        UserProjectAssessmentResponseDTO responseDTO = new UserProjectAssessmentResponseDTO();
//        responseDTO.setUserId(user.getId());
//        responseDTO.setUserName(user.getName());
//        responseDTO.setProjectId(project.getId());
//        responseDTO.setProjectName(project.getName());
//        responseDTO.setAssessments(assessmentDTOs);
//
//        return responseDTO;
//    }
//
//    private AssessResDTO convertToProjectAssessmentDTO(Assess assess) {
//        AssessResDTO dto = assessMapper.toAssessResDto(assess);
//        List<AssessDetail> details = assessDetailRepository.findByAssess(assess);
//        dto.setAssessDetails(details.stream()
//                .map(assessMapper::assessDetailToAssessmentDetailDTO)
//                .collect(Collectors.toList()));
//        return dto;
//    }


}
