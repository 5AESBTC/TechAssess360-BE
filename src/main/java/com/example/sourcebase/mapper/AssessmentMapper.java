package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.AssessDetail;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.resdto.AssessmentDetailResDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectAssessmentResDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssessmentMapper {
    @Mapping(target = "assessor", source = "user")
    @Mapping(target = "assessmentType", source = "assessmentType")
    ProjectAssessmentResDTO assessToProjectAssessmentDTO(Assess assess);

    UserResDTO userToAssessorDTO(User user);

    @Mapping(target = "criteriaId", source = "criteria.id")
    @Mapping(target = "criteriaName", source = "criteria.title")
    @Mapping(target = "questionId", source = "question.id")
    @Mapping(target = "questionText", source = "question.title")
    AssessmentDetailResDTO assessDetailToAssessmentDetailDTO(AssessDetail assessDetail);

}
