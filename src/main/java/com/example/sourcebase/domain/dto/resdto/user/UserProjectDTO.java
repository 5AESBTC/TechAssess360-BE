package com.example.sourcebase.domain.dto.resdto.user;

import com.example.sourcebase.domain.dto.resdto.ProjectResDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProjectDTO {
    private Long id;
    private ProjectResDTO project;
}
