package com.example.sourcebase.controller;

<<<<<<< HEAD
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.service.IUserService;
import com.example.sourcebase.util.ErrorCode;
import com.example.sourcebase.util.ResponseData;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
<<<<<<< HEAD
=======
import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.service.IUserService;
import com.example.sourcebase.util.SuccessCode;
import com.example.sourcebase.util.ResponseData;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
>>>>>>> e96da83 (update source base)
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> 29181f91eab65d5ca609d2bff852822a0eb8f31c

<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

=======
>>>>>>> e96da83 (update source base)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserRestController {
    IUserService userService;

    @PostMapping
    public ResponseData<?> register(@RequestBody RegisterReqDTO registerReqDTO) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            registerReqDTO.setDob(String.valueOf(formatter.parse(registerReqDTO.getDob())));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return ResponseData.builder().data(userService.register(registerReqDTO))
<<<<<<< HEAD
                .code(ErrorCode.CREATED.getCode())
                .message(ErrorCode.CREATED.getMessage())
                .status(ErrorCode.CREATED.getHttpStatus().name())
=======
                .code(SuccessCode.CREATED.getCode())
                .message(SuccessCode.CREATED.getMessage())
                .status(SuccessCode.CREATED.getHttpStatus().name())
>>>>>>> e96da83 (update source base)
                .build();
    }
    @GetMapping("/{id}")
    public ResponseData<?> getUserById(@PathVariable Long id) {
        return ResponseData.builder()
                .status(ErrorCode.GET_SUCCESSFUL.getHttpStatus().name())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(userService.getUserById(id))
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseData.builder()
                .code(ErrorCode.DELETE_SUCCESSFUL.getCode())
                .message(ErrorCode.DELETE_SUCCESSFUL.getMessage())
                .message("Delete user success")
                .build();
    }
    @PatchMapping("/{id}")
    public ResponseData<?> updateUser(@PathVariable Long id, @RequestBody RegisterReqDTO request) {
        return ResponseData.builder()
                .code(ErrorCode.UPDATE_SUCCESSFUL.getCode())
                .message(ErrorCode.UPDATE_SUCCESSFUL.getMessage())
                .data(userService.updateUser(id, request))
                .build();
    }
}
