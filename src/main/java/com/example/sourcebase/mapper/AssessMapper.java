package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Assess;
import com.example.sourcebase.domain.AssessDetail;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.AssessReqDTO;
import com.example.sourcebase.domain.dto.resdto.AssessResDTO;
import com.example.sourcebase.domain.dto.resdto.AssessDetailResDto;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssessMapper {
    AssessMapper INSTANCE = Mappers.getMapper(AssessMapper.class);

    AssessResDTO toAssessResDto(Assess assess);

    Assess toAssess(AssessReqDTO assessReqDto);
    UserResDTO userToAssessorDTO(User user);

//    @Mapping(target = "criteriaId", source = "criteria.id")
//    @Mapping(target = "criteriaName", source = "criteria.title")
//    @Mapping(target = "questionId", source = "question.id")
//    @Mapping(target = "questionText", source = "question.title")
    AssessDetailResDto assessDetailToAssessmentDetailDTO(AssessDetail assessDetail);
}
