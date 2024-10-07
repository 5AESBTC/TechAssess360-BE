package com.example.sourcebase.controller;

import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.service.IAssessService;
import com.example.sourcebase.util.ResponseData;
import com.example.sourcebase.util.SuccessCode;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assess")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
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
    @GetMapping("/list-assess-of-user/{userId}")
    public ResponseEntity<ResponseData<?>> getListAssessByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(assessService.getListAssessByUserId(userId))
                        .build()
        );
    }

    @GetMapping("/is-submit-form")
    public ResponseEntity<ResponseData<?>> isSubmitForm(@RequestParam Long userId, @RequestParam Long toUserId) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.CREATED.getCode())
                        .message(SuccessCode.CREATED.getMessage())
                        .data(assessService.isSubmitForm(userId, toUserId))
                        .build()
        );
    }
}
