package com.example.sourcebase.controller;

import com.example.sourcebase.domain.dto.reqdto.ProjectReqDTO;
import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import com.example.sourcebase.service.IProjectService;
import com.example.sourcebase.util.ResponseData;
import com.example.sourcebase.util.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/projects")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProjectRestController {
    IProjectService projectService;
    @PostMapping("/add")
    public ResponseEntity<ResponseData<?>> createProject(@RequestBody ProjectReqDTO projectRequest) {
      return ResponseEntity.ok(
              ResponseData.builder()
                      .code(SuccessCode.CREATED.getCode())
                      .message(SuccessCode.CREATED.getMessage())
                      .data(projectService.addProject(projectRequest))
                      .build());
    }
}
