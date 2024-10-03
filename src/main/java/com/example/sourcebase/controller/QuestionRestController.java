package com.example.sourcebase.controller;

import com.example.sourcebase.service.IQuestionService;
import com.example.sourcebase.util.ResponseData;
import com.example.sourcebase.util.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class QuestionRestController {
    IQuestionService questionService;

    @GetMapping("/{criteriaId}")
    public ResponseEntity<ResponseData<?>> getAllQuestionsByCriteriaID(@PathVariable Long criteriaId) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(questionService.getAllQuestionsByCriteriaID(criteriaId))
                        .build());
    }
}
