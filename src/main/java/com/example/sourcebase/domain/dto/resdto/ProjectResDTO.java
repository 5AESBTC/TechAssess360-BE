package com.example.sourcebase.domain.dto.resdto;

import com.example.sourcebase.domain.dto.resdto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProjectResDTO {
     Long id;
     String name;
     @DateTimeFormat(pattern = "dd-MM-yyyy")
     LocalDate startDay;
     @DateTimeFormat(pattern = "dd-MM-yyyy")
     LocalDate endDay;
     private List<UserDTO> members;


}
