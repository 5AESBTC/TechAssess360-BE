package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.mapper.AssessDetailMapper;
import com.example.sourcebase.repository.CriteriaRepository;
import com.example.sourcebase.repository.IAssessDetailRepository;
import com.example.sourcebase.mapper.AssessMapper;
import com.example.sourcebase.repository.IAssessRepository;
import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDto;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
import com.example.sourcebase.repository.IUserRepository;
import com.example.sourcebase.service.IAssessService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AssessService implements IAssessService {
    AssessMapper assessMapper = AssessMapper.INSTANCE;
    AssessDetailMapper assessDetailMapper = AssessDetailMapper.INSTANCE;
    IAssessRepository assessRepository;
    IUserRepository userRepository;
    IAssessDetailRepository assessDetailRepository;
    private final CriteriaRepository criteriaRepository;

    @Override
    public AssessResDto updateAssess(AssessReqDTO assessReqDto) {
        ETypeAssess type = null;
        if (assessReqDto.getUserId().equals(assessReqDto.getToUserId())) {
            type = ETypeAssess.SELF;
        } else if(userRepository.findById(Long.valueOf(assessReqDto.getUserId())).get().getUserRoles().equals("MANAGER")) {
            type = ETypeAssess.MANAGER;
        } else {
            type = ETypeAssess.TEAM;
        }

        Assess assess = assessMapper.toAssess(assessReqDto);
        assess.setAssessmentType(type);
        assess.setAssessmentDate(LocalDate.now());
        assessRepository.save(assess);
        // save assess detail

        assessReqDto.getAssessDetails().forEach(item -> {
            item.setAssessId(String.valueOf(assess.getId()));
            assessDetailRepository.save(assessDetailMapper.toAssessDetail(item));
        });
        return assessMapper.toAssessResDto(assess);
    }

    @Override
    public List<AssessResDto> getAssessByUserId(String userId) {
        return null;
    }
}
