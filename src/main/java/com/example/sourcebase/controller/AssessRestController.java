package com.example.sourcebase.controller;

import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
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
}
