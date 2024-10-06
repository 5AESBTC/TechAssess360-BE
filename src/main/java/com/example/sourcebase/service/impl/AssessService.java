package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.AssessDetail;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.mapper.AssessDetailMapper;
import com.example.sourcebase.repository.*;
import com.example.sourcebase.mapper.AssessMapper;
import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDTO;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
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
    ICriteriaRepository criteriaRepository;
    IQuestionRepository questionRepository;

    @Override
    public AssessResDTO updateAssess(AssessReqDTO assessReqDto) {
        ETypeAssess type = null;
        if (assessReqDto.getUserId().equals(assessReqDto.getToUserId())) {
            type = ETypeAssess.SELF;
        } else if(userRepository.findById(Long.valueOf(assessReqDto.getUserId())).get().getUserRoles().equals("MANAGER")) {
            type = ETypeAssess.MANAGER;
        } else {
            type = ETypeAssess.TEAM;
        }
        User userReview = userRepository.findById(Long.valueOf(assessReqDto.getUserId())).get();
        User toUser = userRepository.findById(Long.valueOf(assessReqDto.getToUserId())).get();
        Assess assess = assessMapper.toAssess(assessReqDto);
        assess.setUser(userReview);
        assess.setToUser(toUser);
        assess.setAssessmentType(type);
        assess.setAssessmentDate(LocalDate.now());
        assessRepository.save(assess);
        assessReqDto.getAssessDetails().forEach(item -> {
            AssessDetail assessDetail = assessDetailMapper.toAssessDetail(item);
            assessDetail.setAssess(assess);
            if (item.getCriteriaId().equals("6")|| item.getCriteriaId().equals("7") || item.getCriteriaId().equals("8")) {
                assessDetail.setComment(true);
            }
            if (item.getQuestionId() != null) {
                assessDetail.setQuestion(questionRepository.findById(Long.valueOf(item.getQuestionId())).get());
            }else {
                assessDetail.setQuestion(null);
            }
            assessDetail.setCriteria(criteriaRepository.findById(Long.valueOf(item.getCriteriaId())).get());
            assessDetailRepository.save(assessDetail);
        });
        return assessMapper.toAssessResDto(assess);
    }

    @Override
    public List<AssessResDTO> getAssessByUserId(String userId) {
        return List.of();
    }


}
