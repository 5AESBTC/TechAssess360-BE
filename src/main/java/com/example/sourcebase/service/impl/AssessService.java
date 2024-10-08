package com.example.sourcebase.service.impl;

import com.cloudinary.api.exceptions.NotFound;
import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.AssessDetail;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.UserRole;
import com.example.sourcebase.domain.dto.resdto.AssessDetailResDto;
import com.example.sourcebase.domain.dto.resdto.CriteriaResDTO;
import com.example.sourcebase.domain.dto.resdto.QuestionResDTO;
import com.example.sourcebase.mapper.AssessDetailMapper;
import com.example.sourcebase.mapper.CriteriaMapper;
import com.example.sourcebase.mapper.QuestionMapper;
import com.example.sourcebase.repository.*;
import com.example.sourcebase.mapper.AssessMapper;
import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDTO;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
import com.example.sourcebase.service.IAssessService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AssessService implements IAssessService {
    AssessMapper assessMapper = AssessMapper.INSTANCE;
    AssessDetailMapper assessDetailMapper = AssessDetailMapper.INSTANCE;
    CriteriaMapper criteriaResMapper = CriteriaMapper.INSTANCE;
    QuestionMapper questionResMapper = QuestionMapper.INSTANCE;
    IAssessRepository assessRepository;
    IUserRepository userRepository;
    IAssessDetailRepository assessDetailRepository;
    ICriteriaRepository criteriaRepository;
    IQuestionRepository questionRepository;

    @Override
    public AssessResDTO updateAssess(AssessReqDTO assessReqDto) {
        ETypeAssess type = null;
        List<UserRole> roles = userRepository.findById(Long.valueOf(assessReqDto.getUserId())).get().getUserRoles();
        if (assessReqDto.getUserId().equals(assessReqDto.getToUserId())) {
            type = ETypeAssess.SELF;
        } else if(!roles.isEmpty()) {
            for (UserRole role : roles) {
                if (role.getRole().getName().equalsIgnoreCase(ETypeAssess.MANAGER.name())) {
                    type = ETypeAssess.MANAGER;
                    break;
                }
            }
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
    public List<AssessResDTO> getListAssessByUserId(Long userId) {
        return assessRepository.getListAssessByUserId(userId).stream()
                .map(assess -> {
                    AssessResDTO assessResDTO = assessMapper.toAssessResDto(assess);
                    assessResDTO.setAssessDetails(assessResDTO.getAssessDetails().stream()
                            .peek(assessDetail -> assessDetail.setAssessId(assessResDTO.getId()))
                            .collect(Collectors.toList()));
                    return assessResDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public boolean isSubmitForm(Long userId, Long toUserId) {
        return false;
    }

    @Override
    public AssessResDTO getAssess(Long userId) {
        AssessResDTO myAssess = assessMapper.toAssessResDto(assessRepository.findByToUserIdAndAssessmentType(userId, ETypeAssess.SELF));
        myAssess.getAssessDetails().forEach(item -> item.setAssessId(myAssess.getId()));
        return myAssess;
    }
}
