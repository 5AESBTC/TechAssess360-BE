package com.example.sourcebase.domain.dto.resdto;

import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.domain.enumeration.ETypeAssess;
import com.example.sourcebase.domain.enumeration.ETypeSubmit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProjectAssessmentResDTO {
     Long id;
     UserResDTO assessor;
     LocalDate assessmentDate;
     ETypeAssess assessmentType;
     int totalPoint;
     List<AssessmentDetailResDTO> details;
}
