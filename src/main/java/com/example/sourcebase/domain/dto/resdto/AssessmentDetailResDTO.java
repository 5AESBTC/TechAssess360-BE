package com.example.sourcebase.domain.dto.resdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AssessmentDetailResDTO {
     Long id;
     Long criteriaId;
     String criteriaName;
     Long questionId;
     String questionText;
     int value;
     String description;
     boolean isComment;
}
