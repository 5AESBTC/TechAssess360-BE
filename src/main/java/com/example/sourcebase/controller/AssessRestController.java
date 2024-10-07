package com.example.sourcebase.controller;

import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.reqdto.UserProjectAssessmentRequestDTO;
import com.example.sourcebase.domain.dto.resdto.UserProjectAssessmentResponseDTO;
import com.example.sourcebase.service.IAssessResultService;
import com.example.sourcebase.service.IAssessService;
import com.example.sourcebase.util.ResponseData;
import com.example.sourcebase.util.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assess")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AssessRestController {
    IAssessService assessService;
    IAssessResultService assessResultService;
    @PostMapping("/save-assess")
    @CrossOrigin
    public ResponseEntity<ResponseData<?>> saveAssess(@RequestBody AssessReqDTO assessReqDto) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.CREATED.getCode())
                        .message(SuccessCode.CREATED.getMessage())
                        .data(assessService.updateAssess(assessReqDto))
                        .build()
        );
    }
    @GetMapping("/user-project")
    public ResponseEntity<ResponseData<?>> getUserProjectAssessments(
            @RequestParam Long userId,
            @RequestParam Long projectId) {
        UserProjectAssessmentRequestDTO requestDTO = new UserProjectAssessmentRequestDTO();
        requestDTO.setUserId(userId);
        requestDTO.setProjectId(projectId);
        UserProjectAssessmentResponseDTO responseDTO = assessResultService.getUserProjectAssessments(requestDTO);
        return ResponseEntity.ok( ResponseData.builder()
                .code(SuccessCode.GET_SUCCESSFUL.getCode())
                .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                .data(responseDTO)
                .build());
    }
}
