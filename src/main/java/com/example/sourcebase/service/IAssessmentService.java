package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.UserProjectAssessmentRequestDTO;
import com.example.sourcebase.domain.dto.resdto.UserProjectAssessmentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface IAssessmentService {
    UserProjectAssessmentResponseDTO getUserProjectAssessments(UserProjectAssessmentRequestDTO requestDTO);
}
