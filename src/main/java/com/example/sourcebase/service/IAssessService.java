package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.reqdto.UserProjectAssessmentRequestDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDTO;
import com.example.sourcebase.domain.dto.resdto.UserProjectAssessmentResponseDTO;

import java.util.List;

public interface IAssessService {
    AssessResDTO updateAssess(AssessReqDTO assessReqDto);
    List<AssessResDTO> getAssessByUserId(String userId);

//    UserProjectAssessmentResponseDTO getUserProjectAssessments(UserProjectAssessmentRequestDTO requestDTO);
}
