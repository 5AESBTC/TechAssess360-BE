package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDto;

import java.util.List;

public interface IAssessService {
    AssessResDto updateAssess(AssessReqDTO assessReqDto);
    List<AssessResDto> getAssessByUserId(String userId);
}
