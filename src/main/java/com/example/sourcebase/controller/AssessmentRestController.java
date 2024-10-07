package com.example.sourcebase.controller;

import com.example.sourcebase.domain.dto.reqdto.UserProjectAssessmentRequestDTO;
import com.example.sourcebase.domain.dto.resdto.UserProjectAssessmentResponseDTO;
import com.example.sourcebase.service.IAssessmentService;
import com.example.sourcebase.util.ResponseData;
import com.example.sourcebase.util.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assessments")
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AssessmentRestController {

     IAssessmentService assessmentService;

    @GetMapping("/user-project")
    public ResponseEntity<ResponseData<?>> getUserProjectAssessments(
            @RequestParam Long userId,
            @RequestParam Long projectId) {
        UserProjectAssessmentRequestDTO requestDTO = new UserProjectAssessmentRequestDTO();
        requestDTO.setUserId(userId);
        requestDTO.setProjectId(projectId);
        UserProjectAssessmentResponseDTO responseDTO = assessmentService.getUserProjectAssessments(requestDTO);
        return ResponseEntity.ok( ResponseData.builder()
                .code(SuccessCode.GET_SUCCESSFUL.getCode())
                .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                .data(responseDTO)
                .build());
    }
}
